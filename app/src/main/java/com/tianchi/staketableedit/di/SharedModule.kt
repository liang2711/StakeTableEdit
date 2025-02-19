package com.tianchi.staketableedit.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tianchi.staketableedit.data.PermissionState
import com.tianchi.staketableedit.database.AppDatabase
import com.tianchi.staketableedit.database.DataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedModule {
    /**
     *  @Provides: 该注解告诉 Hilt 该方法是一个提供器函数，用于创建或提供一个依赖项。在这个例子中，providePermissionState 函数提供了一个 MutableStateFlow<PermissionState> 实例。
     * @Singleton: 该注解表示 Hilt 会确保返回的 MutableStateFlow<PermissionState> 是一个单例（即整个应用中只有一个实例）。
     * @PermissionStateFlow: 这是你自定义的 Qualifier 注解，用来区分同一类型的不同依赖。在这个例子中，它表示这个 StateFlow 是用于权限状态的。
     * */

    @Provides
    @PermissionStateFlow
    @Singleton
    fun providePermissionState(): MutableStateFlow<PermissionState> {
        return MutableStateFlow(PermissionState.None)
    }

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database.db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): DataDao {
        return appDatabase.dataDao()
    }
}
/**
 * @Qualifier: 用来创建自定义的注解，使得 Hilt 可以区分同一类型的多个不同依赖项。
 * @Retention(AnnotationRetention.BINARY): 表示该注解会在编译时保留，适用于字节码，并能在运行时用于反射。
 * */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PermissionStateFlow