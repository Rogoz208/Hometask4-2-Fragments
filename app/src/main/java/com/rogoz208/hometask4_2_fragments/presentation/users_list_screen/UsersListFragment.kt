package com.rogoz208.hometask4_2_fragments.presentation.users_list_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.hometask4_2_fragments.R
import com.rogoz208.hometask4_2_fragments.databinding.FragmentUsersListBinding
import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity
import com.rogoz208.hometask4_2_fragments.presentation.edit_user_screen.EditUserFragment
import com.rogoz208.hometask4_2_fragments.presentation.users_list_screen.recycler.UserItemClickListener
import com.rogoz208.hometask4_2_fragments.presentation.users_list_screen.recycler.UsersAdapter
import com.rogoz208.hometask4_2_fragments.presentation.users_list_screen.recycler.UsersDiffCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val binding by viewBinding(FragmentUsersListBinding::bind)
    private val viewModel by viewModels<UsersListViewModel>()

    private var adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(EditUserFragment.REQUEST_KEY) { key, bundle ->
            val userSaved = bundle.getBoolean(EditUserFragment.SUCCESS_EXTRA_KEY)
            if (userSaved) {
                viewModel.onUserUpdated()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
    }

    private fun initViewModel() {
        with(viewModel) {
            usersListLiveData.observe(viewLifecycleOwner) { users ->
                fillRecyclerView(users)
            }
        }
    }

    private fun initRecyclerView() {
        adapter.setOnItemClickListener(object : UserItemClickListener {
            override fun onItemClick(item: UserEntity, position: Int) {
                openEditUserScreen(item, position)
            }
        })
        with(binding) {
            usersListRecyclerView.layoutManager = LinearLayoutManager(context)
            usersListRecyclerView.adapter = adapter
        }

    }

    private fun fillRecyclerView(users: List<UserEntity>) {
        val usersDiffCallback = UsersDiffCallback(adapter.data, users)
        val result = DiffUtil.calculateDiff(usersDiffCallback, true)
        adapter.data = users
        result.dispatchUpdatesTo(adapter)
    }

    private fun openEditUserScreen(user: UserEntity, position: Int) {
        (requireActivity() as? EditUserClickListener)?.onEditUserClicked(user, position)
    }

    interface EditUserClickListener {
        fun onEditUserClicked(user: UserEntity, position: Int)
    }

    companion object {
        const val USERS_LIST_FRAGMENT_TAG = "USERS_LIST_FRAGMENT_TAG"

        fun newInstance() = UsersListFragment()
    }
}