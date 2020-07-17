package com.google.eyadhewware.reglog.Secreens

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.eyadhewware.reglog.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login_secreen.*


class LoginSecreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_secreen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoginBTN?.apply { setOnClickListener {
            signIn()
        } }
        Text1. setOnClickListener {
            findNavController().navigate(R.id.action_loginSecreen_to_registerSecreen)

        }

    }

    fun signIn(){
        if (EmailLogin.text.toString().isEmpty()
            && PasswordLogin.text.toString().isEmpty()){
            Toast.makeText(activity , "enter username and password", Toast.LENGTH_SHORT).show()
        }
        else if ( EmailLogin.text.toString().isEmpty()){
            Toast.makeText(activity , "enter username", Toast.LENGTH_SHORT).show()
        }
        else if ( PasswordLogin.text.toString().isEmpty()){
            Toast.makeText(activity , "enter password", Toast.LENGTH_SHORT).show()
        }
        else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(EmailLogin.text.toString() , PasswordLogin.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        findNavController().navigate(R.id.action_loginSecreen_to_homeSecreen)
                    }
                    else{
                        Handler().postDelayed({
                            Toast.makeText(activity , "you should have accuont first", Toast.LENGTH_LONG).show()
                        },3000)

                        findNavController().navigate(R.id.action_loginSecreen_to_registerSecreen)

                    }
                }
        }
    }

}