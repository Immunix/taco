package com.candybytes.taco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.candybytes.taco.databinding.FragmentCategoryListBinding
import com.candybytes.taco.ui.adapters.CategoryAdapter
import com.candybytes.taco.ui.vm.CategoriesViewModel
import com.candybytes.taco.vo.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment() {

    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCategoryListBinding.inflate(layoutInflater, container, false).apply {
            viewModel = this@CategoryListFragment.viewModel
            lifecycleOwner = this@CategoryListFragment

            recyclerCategory.apply {
                categoryAdapter = CategoryAdapter()

                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = categoryAdapter
            }
        }.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.totalTacoCategories.observe(viewLifecycleOwner, Observer {
            categoryAdapter.submitList(it)
        })
    }

}
