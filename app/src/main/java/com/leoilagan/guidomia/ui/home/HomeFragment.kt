package com.leoilagan.guidomia.ui.home

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

import com.leoilagan.guidomia.BaseApplication
import com.leoilagan.guidomia.data.local.CarInfo
import com.leoilagan.guidomia.databinding.FragmentHomeBinding
import com.leoilagan.guidomia.ui.base.BaseFragment
import com.leoilagan.guidomia.ui.home.adapter.CarInfoAdapter
import com.leoilagan.guidomia.ui.home.adapter.CustomItemClickedListener
import kotlinx.android.synthetic.main.fragment_home.*





class HomeFragment : BaseFragment<HomeViewModel>(), CustomItemClickedListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentHomeBinding.inflate(inflater,container,false)

        initRootView(binding)
        addObservers()
        return binding.root
    }



    private fun initRootView(rootView : FragmentHomeBinding) {
        val adapter = CarInfoAdapter(this)
        rootView.apply {
            recyclerView.adapter = adapter
            swipeRefresh.isRefreshing = true
            swipeRefresh.setOnRefreshListener {
                viewModel.getData()
                resetSearch()
            }

            txtFilterMake.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val makeQuery = txtFilterMake.text.toString()
                    //close on screen keyboard
                    txtFilterMake.clearFocus()
                    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(txtFilterMake!!.getWindowToken(), 0)

                    viewModel.searchForMaker(makeQuery)


                }
                false
            }

            txtFilterModel.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val modelQuery =  txtFilterModel.text.toString()
                    //close on screen keyboard
                    txtFilterModel.clearFocus()
                    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(txtFilterModel!!.getWindowToken(), 0)

                    viewModel.searchForMaker(modelQuery)


                }
                false
            }


        }

    }


    private fun addObservers() {
        viewModel.carList.observe(viewLifecycleOwner,{
            val adapter =  recycler_view.adapter as CarInfoAdapter
            adapter.apply {

                submitList(it)
            }
            swipe_refresh.isRefreshing = false
        })
    }

    private fun resetSearch() {
        binding.txtFilterModel.text.clear()
        binding.txtFilterMake.text.clear()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as BaseApplication).appComponent.inject(this)
    }

    override fun itemClicked(f: CarInfo?) {

    }
}