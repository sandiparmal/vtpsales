package com.vtpsales.vtpsales.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vtpsales.vtpsales.R
import com.vtpsales.vtpsales.data.utils.Resource
import com.vtpsales.vtpsales.data.utils.autoCleared
import com.vtpsales.vtpsales.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var binding: LoginFragmentBinding by autoCleared()
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            setupObserver(binding.editText.text.toString(),
            binding.editText2.text.toString())
        }

    }

    private fun setupObserver(email : String, password : String) {

        viewModel.start(email, password)
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let {
                        binding.progressBar.visibility = View.INVISIBLE
                        if (it.role.equals("Admin", true)) {
                            //Admin User
                            findNavController().navigate(
                                R.id.action_loginFragment_to_serviceCentersFragment,
                                bundleOf("id" to it.id)
                            )
                        } else {
                            // normal sales user
                            findNavController().navigate(
                                R.id.action_loginFragment_to_fillServiceCenterFragment,
                                bundleOf("id" to it.id)
                            )
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE

            }
        })
    }

}