package com.tianchi.staketableedit.ui.files

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.tianchi.staketableedit.R
import java.io.File
import java.nio.file.Files

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilesScreen(
    fileState: FileState,
    onFileSelected: (String) -> Unit,
    onCancel: () -> Unit
) {
    val action = remember { mutableStateOf<FileAction>(FileAction.None(onBack = { onCancel() })) }

    //设置copy或者select里的parentfile路径
    LaunchedEffect(fileState.current) {
        fileState.onContentChange()
    }
    //按下返回健且条件满足时
    BackHandler(fileState.current != fileState.root) {
        fileState.goToParent()
    }
    val listState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = action.value.topBarTitle, maxLines = 1)
                }, navigationIcon = {
                    action.value.navigation?.let {
                        IconButton(
                            onClick = {
                                it.second.invoke(action.value)
                            }
                        ) {
                            Icon(imageVector = it.first, contentDescription = null)
                        }
                    }
                }, actions = {
                    action.value.actions.forEach { item ->
                        IconButton(
                            onClick = item.second
                        ) {
                            Icon(imageVector = item.first, contentDescription = null)
                        }
                    }
                }, scrollBehavior = scrollBehavior
            )
        }

//        floatingActionButton = {
//            AnimatedVisibility(
        //当下拉到底时不显示
//                visible = listState.canScrollForward,
//                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
//                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
//            ) {
//                FloatingActionButton(
//                    onClick = {
//
//                    }
//                ) {
//                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
//                }
//            }
//        }
        , modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            contentPadding = innerPadding
        ) {
            if (fileState.parent != null) item {
                ListItem(
                    headlineContent = {
                        Text(text = "..")
                    }, modifier = Modifier.clickable {
                        fileState.goToParent()
                    }, leadingContent = {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                )
            }
            items(fileState.items) { item ->
                ListItem(
                    headlineContent = {
                        Text(text = item.name)
                    }, leadingContent = {
                        if (item.isDirectory) {
                            Icon(
                                painter = painterResource(R.mipmap.directory),
                                contentDescription = "Vector Image",
                                modifier = Modifier.size(30.dp),  // 控制图片的大小
                            )
                        }
                        if (item.isFile) {
                            Icon(
                                painter = painterResource(R.mipmap.file),
                                contentDescription = "Vector Image",
                                modifier = Modifier.size(30.dp)  // 控制图片的大小
                            )
                        }
                    },
//                    trailingContent = {
//                        val expanded = remember { mutableStateOf(false) }
//                        IconButton(
//                            onClick = {
//                                expanded.value = true
//                            }
//                        ) {
//                            Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = null)
//                        }
//                    }
                    modifier = Modifier.clickable {
                        if (item.isDirectory) fileState.current = item.path
                        if (item.isFile) {
                            var fileActionCopy: FileAction.Select = FileAction.Select(
                                source = item,
                                onPasteResult = { str ->
                                    //filePath = str
                                    onFileSelected(str)
                                    //返回数据
//                                    action.value = FileAction.None
//                                    navController.previousBackStackEntry?.savedStateHandle?.set("resultKey", str)
//                                    navController.popBackStack()
//                                    isLoading = true
//
//                                    var excelUtils=ExcelUtils()
//                                    var excelData=excelUtils.parseExcel(str)
//
//                                    isLoading = false
//                                    viewModel.setData(excelData);
//                                    navController.popBackStack()

                                }, onCancel = {
                                    action.value = FileAction.None(onBack = { onCancel() })
                                }
                            )
                            action.value = fileActionCopy
                        }
                    }
                )
            }
        }
    }
}

fun Context.openExternal(file: File) {
    if (file.isDirectory) return
    val contentType = Files.probeContentType(file.toPath())
    val uri = FileProvider.getUriForFile(this, "$packageName.provider", file)
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        setDataAndType(uri, contentType)
    }
    startActivity(intent)
}

class FileState {
    var items by mutableStateOf(emptyList<File>())
    var root: String by mutableStateOf(Environment.getExternalStorageDirectory().path)
    var current: String by mutableStateOf(root)
    var parent: String? by mutableStateOf(null)
    val listener = mutableListOf<(String) -> Unit>()

    fun onContentChange() {
        listener.forEach {
            it(current)
        }
        val file = File(current)
//        val files = (file.listFiles() ?: arrayOf())
//            .groupBy { it.isDirectory }
//            .flatMap {
//                it.value.sortedBy { it.name }
//            }
        val files = (file.listFiles() ?: arrayOf())
            .sortedBy { it.name }  // 按文件名排序
            .flatMap {
                // 先获取所有文件夹，再获取所有后缀为 .xls 的文件
                when {
                    it.isDirectory -> listOf(it)  // 如果是目录，添加到列表中
                    it.extension.equals(
                        "xls",
                        ignoreCase = true
                    ) -> listOf(it)  // 如果是 .xls 文件，添加到列表中
                    else -> emptyList()  // 否则不做任何处理
                }
            }
        parent = if (current == root) null else file.parentFile?.path
        items = files
    }

    fun goToParent() {
        parent?.let { current = it }
    }
}

sealed interface FileAction {
    val topBarTitle: String
    val navigation: Pair<ImageVector, FileAction.() -> Unit>?
    val actions: List<Pair<ImageVector, () -> Unit>>

    data class None(val onBack: FileAction.() -> Unit = {}) : FileAction {
        override val topBarTitle: String = "文件选择"
        override val navigation: Pair<ImageVector, (FileAction) -> Unit>? =
            Icons.AutoMirrored.Rounded.ArrowBack to onBack
        override val actions: List<Pair<ImageVector, () -> Unit>> = emptyList()
    }

    class Select(
        val source: File,
        val onPasteResult: (String) -> Unit,
        val onCancel: FileAction.() -> Unit
    ) : FileAction {
        fun sure() {
            Log.d("MainActivity", "${source.path} ${source.name}")
            onPasteResult(source.path);
        }

        override val topBarTitle: String = "已经选中 ${source.name} "
        override val navigation: Pair<ImageVector, FileAction.() -> Unit>? =
            Icons.Rounded.Clear to onCancel
        override val actions: List<Pair<ImageVector, () -> Unit>> = listOf(
            Icons.Default.Done to ::sure
        )
    }

    class Copy(
        val source: File,
        val onPasteResult: (Boolean, String) -> Unit,
        val onCancel: FileAction.() -> Unit
    ) : FileAction {
        private var targetDir: File = source.parentFile
        fun onTargetDirChange(it: String) {
            targetDir = File(it)
        }

        fun paste() {
            val filename = source.name
            val target = File(targetDir, filename)
            if (target.exists()) {
                onPasteResult(false, "文件已存在")
            } else {
                source.copyRecursively(target)
                onPasteResult(true, "复制成功")
            }
        }

        override val topBarTitle: String = "复制 ${source.name} 到.."
        override val navigation: Pair<ImageVector, FileAction.() -> Unit>? =
            Icons.Rounded.Clear to onCancel
        override val actions: List<Pair<ImageVector, () -> Unit>> = listOf(
            Icons.Default.Done to ::paste
        )
    }
}