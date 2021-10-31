package com.vtpsales.vtpsales.ui.servicecenterdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vtpsales.vtpsales.R

class ServiceCenterDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ServiceCenterDetailsFragment()
    }

    private lateinit var viewModel: ServiceCenterDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.service_center_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServiceCenterDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}