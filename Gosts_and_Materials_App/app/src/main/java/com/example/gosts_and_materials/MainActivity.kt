package com.example.gosts_and_materials


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gosts_and_materials.presentation.NavGraphs
import com.example.gosts_and_materials.presentation.destinations.GostItemInfoScreenDestination
import com.example.gosts_and_materials.presentation.destinations.MaterialItemInfoScreenDestination
import com.example.gosts_and_materials.presentation.item_gost_info.GostItemInfoScreen
import com.example.gosts_and_materials.presentation.item_material_info.MaterialItemInfoScreen
import com.example.gosts_and_materials.presentation.navDestination
import com.example.gosts_and_materials.presentation.ui.theme.Gosts_and_MaterialsTheme
import com.example.gosts_and_materials.utils.BottomBarDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private lateinit var requestMultiplePermission: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        val file = File(
            id = "1",
            name = "ГОСТ 2601 - 84.pdf",
            type = "PDF",
            url = "http://www.ukrtop.info/gost/210.pdf"
        )
        startDownloadingFile(
            file,
            success = {
                Log.d("MainScreen", "Success")
            },
            failed = {
                Log.d("MainScreen", "Failed")
            },
            running = {
                Log.d("MainScreen", "Running")
            }
        )

        requestMultiplePermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            var isGranted = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.forEach { (s, b) ->
                    isGranted = b
                }
            }

            if (!isGranted) {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
            }
        }
    */
        setContent {
            MainScreenView()
        }
    }
}
/*
    fun startDownloadingFile(
        file: File,
        success: (String) -> Unit,
        failed: (String) -> Unit,
        running: () -> Unit
    ) {
        val data = Data.Builder()

        data.apply {
            putString(KEY_FILE_NAME, file.name)
            putString(KEY_FILE_URL, file.url)
            putString(KEY_FILE_TYPE, file.type)
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val fileDownloadWorker = OneTimeWorkRequestBuilder<FileDownloadWorker>()
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()

        Log.d("StartDownloading", "startDownloadingFile: $file")
        val workManager = WorkManager.getInstance(applicationContext)

       workManager.enqueueUniqueWork(
            "oneFileDownloadWork_${System.currentTimeMillis()}",
           ExistingWorkPolicy.KEEP,
           fileDownloadWorker
       )
        workManager.beginUniqueWork(
            "oneFileDownloadWork_${System.currentTimeMillis()}",
            ExistingWorkPolicy.KEEP,
            fileDownloadWorker
        ).enqueue()

        workManager.getWorkInfoByIdLiveData(fileDownloadWorker.id)
            .observe(this) { info ->
                info?.let {
                   Log.d("Wokinfo", "$info")
                    when (it.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            success(it.outputData.getString(KEY_FILE_URI) ?: "")
                        }
                        WorkInfo.State.FAILED -> {
                            failed("Downloading failed!")
                        }
                        WorkInfo.State.RUNNING -> {
                            running()
                        }
                        else -> {
                            failed("Something went wrong")
                        }
                    }
                }
            }
        }
*/

@Composable
fun MainScreenView() {
    Gosts_and_MaterialsTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(navController)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController
                )
                {
                    composable(GostItemInfoScreenDestination) {
                        GostItemInfoScreen(
                            itemGost = navArgs.itemGost
                        )
                    }
                    composable(MaterialItemInfoScreenDestination) {
                        MaterialItemInfoScreen(
                            itemMaterial = navArgs.itemMaterial,
                            navigator = destinationsNavigator
                        )
                    }
//                        composable(ResultMechScreenDestination){
//                            ResultMechScreen(bodyString = navArgs.bodyString)
//                        }
                    /*
                    { file ->
                        startDownloadingFile(
                            file,
                            success = {
                                Log.d("MainScreen", "Success")
                            },
                            failed = {
                                Log.d("MainScreen", "Failed")
                            },
                            running = {
                                Log.d("MainScreen", "Running")
                            }
                        )
                }
                */
                }
            }


        }
    }
}

@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination = navController.currentBackStackEntryAsState()
        .value?.navDestination
//        for (i in navController.backQueue) {
//            println(i.destination.route)
//        }
    BottomNavigation {
        BottomBarDestination.values().forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigateTo(destination.direction) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(destination.icon, contentDescription = destination.label) },
                label = { Text(destination.label) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    MainScreenView()
}



