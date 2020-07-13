package com.google.eyadhewware.reglog.Secreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.eyadhewware.reglog.R
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
            if (UserName.text.toString().isEmpty()
                && Password.text.toString().isEmpty()){
                Toast.makeText(activity , "enter username and password", Toast.LENGTH_SHORT).show()
            }
            else if ( UserName.text.toString().isEmpty()){

                Toast.makeText(activity , "enter username", Toast.LENGTH_SHORT).show()

            }
            else if ( Password.text.toString().isEmpty()){

                Toast.makeText(activity , "enter password", Toast.LENGTH_SHORT).show()

            }
            else{

                findNavController().navigate(R.id.action_loginSecreen_to_homeSecreen)
            }
        } }
        Text1. setOnClickListener {
            findNavController().navigate(R.id.action_loginSecreen_to_registerSecreen)

        }

    }

}