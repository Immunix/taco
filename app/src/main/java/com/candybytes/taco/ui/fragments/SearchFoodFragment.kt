package com.candybytes.taco.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.candybytes.taco.R
import com.candybytes.taco.databinding.FragmentSearchFoodBinding
import com.candybytes.taco.ui.adapters.SearchFoodAdapter
import com.candybytes.taco.ui.util.onQueryTextChanged
import com.candybytes.taco.ui.vm.SearchFoodViewModel
import com.candybytes.taco.vo.Food
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFoodFragment : Fragment(), SearchFoodAdapter.OnItemClickListener {

    private lateinit var searchFoodAdapter: SearchFoodAdapter
    private val viewModel: SearchFoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentSearchFoodBinding.inflate(layoutInflater, container, false).apply {
            viewModel = this@SearchFoodFragment.viewModel
            lifecycleOwner = this@SearchFoodFragment
            setHasOptionsMenu(true)
            recyclerFoodSearch.apply {
                searchFoodAdapter = SearchFoodAdapter(this@SearchFoodFragment)
                adapter = searchFoodAdapter
            }

        }.root
    }

    override fun onItemClick(food: Food) {
        // idk how to parcelize the entity

        val action = SearchFoodFragmentDirections.actionSearchFoodFragmentToFoodFragment()
        findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.info.observe(viewLifecycleOwner, Observer {
            searchFoodAdapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }
    }

}
