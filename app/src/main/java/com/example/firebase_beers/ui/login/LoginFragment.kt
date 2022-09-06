package com.example.firebase_beers.ui.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.firebase_beers.R
import com.example.firebase_beers.databinding.FragmentFavouriteBinding
import com.example.firebase_beers.databinding.FragmentLoginBinding
import com.example.firebase_beers.databinding.FragmentMainBinding
import com.example.firebase_beers.ui.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var sEmail: String
    private lateinit var sPassword: String

    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val viewModel by viewModels<MainViewModel>()
        binding = FragmentLoginBinding.bind(view)

        auth = Firebase.auth

        val signupButton = binding.signupButton
        val loginButton = binding.loginButton
        val googleButton = binding.googleSignIn
        val username = binding.usernameV
        val password = binding.passwordV

        val TAG = "firebaselogin"

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("615712074359-bmnac9u2fligphlj8mg2jrvo1860ktfd.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(),gso)


        signupButton.setOnClickListener(){

            sEmail = username.text.toString().trim()
            sPassword = password.text.toString().trim()

            auth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(requireActivity(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }


        }

        loginButton.setOnClickListener(){
            sEmail = username.text.toString().trim()
            sPassword = password.text.toString().trim()
            auth.signInWithEmailAndPassword(sEmail,sPassword)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(requireActivity(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }

        googleButton.setOnClickListener(){
            googleSignIn()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount = task.result
            if (account != null){
                updateUIgoogle(account)
            }
            else{
                Toast.makeText(requireActivity(),task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateUIgoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(){
            if (it.isSuccessful){
                this.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
            else{
                Toast.makeText(requireActivity(),it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){
            this.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
}