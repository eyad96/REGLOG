package com.google.eyadhewware.reglog.Secreens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.eyadhewware.reglog.R
import com.google.firebase.auth.FirebaseAuth

class HomeSecreen :Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_secreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tool_bar_menu , menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId ){
           R.id.Logout -> FirebaseAuth.getInstance().signOut()
        }
        findNavController().navigate(R.id.action_homeSecreen_to_loginSecreen)
        return super.onOptionsItemSelected(item)

    }


}

