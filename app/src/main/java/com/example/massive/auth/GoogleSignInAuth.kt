package com.example.massive.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.example.massive.R
import com.example.massive.view.activities.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class GoogleSignInAuth(private val activity: FragmentActivity, private val load: View) {
    private lateinit var auth: FirebaseAuth
    private lateinit var signInClient: GoogleSignInClient
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var bundle: Bundle

    fun initialize() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        signInClient = GoogleSignIn.getClient(activity, gso)
        auth = FirebaseAuth.getInstance()
        bundle = Bundle()

        launcher =
            activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleResult(task)
                }else{
                    load.visibility = View.INVISIBLE
                }
            }
    }

    fun signInGoogle() {
        val intent = signInClient.signInIntent
        launcher.launch(intent)
    }

    private fun handleResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                firebaseAuthWithGoogle(account)
            }
        } else {
            Toast.makeText(activity, task.exception.toString(), Toast.LENGTH_SHORT).show()
            load.visibility = View.INVISIBLE
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                bundle.putString("email", it.result.user?.email)
                bundle.putString("name", it.result.user?.displayName)
                sendEmailVerification()
            } else {
                Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                load.visibility = View.INVISIBLE
            }
        }
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful) {
                updateUI(user)
                Toast.makeText(activity, "berhasil masuk dengan google", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                load.visibility = View.INVISIBLE
            }
        }
    }

    private fun updateUI(user: FirebaseUser) {
        if (user != null) {
            if (user.isEmailVerified) {
                val intent = Intent(activity, HomeActivity::class.java)
                intent.putExtras(bundle)
                activity.startActivity(intent)
            } else {
                Toast.makeText(activity, "email belum diverifikasi", Toast.LENGTH_SHORT).show()
                load.visibility = View.INVISIBLE
            }
        }
    }

    fun signOutGoogle(){
        signInClient.signOut()
        auth.signOut()
    }
}