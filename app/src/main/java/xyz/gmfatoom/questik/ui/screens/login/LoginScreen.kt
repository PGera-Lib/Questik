package xyz.gmfatoom.questik.ui.screens.login

import android.content.res.Resources
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Login
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.gmfatoom.questik.R
import xyz.gmfatoom.questik.utils.LoadingState

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import xyz.gmfatoom.questik.ui.drawer.DrawerScreens
import xyz.gmfatoom.questik.utils.AUTH
import xyz.gmfatoom.questik.utils.showToast

@JvmOverloads
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = viewModel()) {

    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.loadingState.collectAsState()
    viewModel.initLoginState()


    // Equivalent of onActivityResult
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            viewModel.signWithCredential(credential)
        } catch (e: ApiException) {
            Log.w("TAG", "Google sign in failed", e)
        }
    }




    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TopAppBar(
                    elevation = 1.dp,
                    title = {
                        Text(text = "Login")
                    },
                    backgroundColor = androidx.compose.material.MaterialTheme.colors.primary,
                    contentColor = androidx.compose.material.MaterialTheme.colors.onPrimary,

                    /*
                    navigationIcon = {
                        IconButton(onClick = { *//*TODO*//* }) {
                            Icon(
                                imageVector = Icons.Rounded.Login,
                                contentDescription = null,
                            )
                        }
                    },*/
                    actions = {
                        IconButton(onClick = { Firebase.auth.signOut()}) {
                            Icon(
                                imageVector = Icons.Rounded.ExitToApp,
                                contentDescription = null,
                            )
                        }
                    }
                )
                if (state.status == LoadingState.Status.RUNNING) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = userEmail,
                        label = {
                            Text(text = "Email")
                        },
                        onValueChange = {
                            userEmail = it
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        value = userPassword,
                        label = {
                            Text(text = "Password")
                        },
                        onValueChange = {
                            userPassword = it
                        }
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        enabled = userEmail.isNotEmpty() && userPassword.isNotEmpty(),
                        content = {
                            Text(text = "Login")
                        },
                        onClick = {
                            viewModel.signInWithEmailAndPassword(userEmail.trim(), userPassword.trim())
                        }
                    )

         /*           Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.caption,
                        text = "Login with"
                    )*/

                    Spacer(modifier = Modifier.height(18.dp))

                    val context = LocalContext.current
                    val token = stringResource(R.string.default_questic_client_id)

/*                    OutlinedButton(
                        border = ButtonDefaults.outlinedBorder.copy(width = 1.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        onClick = {
                            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(token)
                                .requestEmail()
                                .build()

                            val googleSignInClient = GoogleSignIn.getClient(context, gso)
                            launcher.launch(googleSignInClient.signInIntent)
                        },
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Icon(
                                        tint = Color.Unspecified,
                                        painter = painterResource(id = com.google.android.gms.base.R.drawable.googleg_standard_color_18),
                                        contentDescription = null,
                                    )
                                    Text(
                                        style = MaterialTheme.typography.button,
                                        color = MaterialTheme.colors.onSurface,
                                        text = "Google"
                                    )
                                    Icon(
                                        tint = Color.Transparent,
                                        imageVector = Icons.Default.MailOutline,
                                        contentDescription = null,
                                    )
                                }
                            )
                        }
                    )*/

                    when(state.status) {
                        LoadingState.Status.SUCCESS -> {
                            Text(text = "Success")
                            LaunchedEffect(state.status){
                                state.status.let {
                                    if (AUTH.currentUser!=null) {
                                        showToast("Вы нам подходите")
                                    navController.navigate(DrawerScreens.Home.route) } else {
                                        showToast("Что-то пошло не так")
                                    }


                                }
                            }
                        }
                        LoadingState.Status.FAILED -> {
                            showToast("Ошибка ввода учетных данных, либо вы не прошли естественный отбор")
                            Text(text = state.msg ?: "Error")
                        }
                        else -> {}
                    }
                }
            )
        }
    )
}

/*@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    questikTheme(true) {
        LoginScreen()
    }
}*/
