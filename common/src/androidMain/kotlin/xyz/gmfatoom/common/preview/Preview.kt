package xyz.gmfatoom.common.preview

import androidx.compose.runtime.Composable


import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import moe.tlaster.precompose.viewmodel.viewModel
import xyz.gmfatoom.common.di.AppModule
import xyz.gmfatoom.common.requestik.presentation.request.RequestListScreen
import xyz.gmfatoom.common.requestik.presentation.request.RequestListViewModel

@Preview
@Composable
fun RequestScreenPreview (){
    val  appModule = AppModule(LocalContext.current.applicationContext)
    val requestViewModel = viewModel {
        RequestListViewModel(appModule.requestikDataSource)
    }
    val onEvent = requestViewModel::onEvent
    RequestListScreen(
        viewModel = requestViewModel,
        onEvent = onEvent
    )
}