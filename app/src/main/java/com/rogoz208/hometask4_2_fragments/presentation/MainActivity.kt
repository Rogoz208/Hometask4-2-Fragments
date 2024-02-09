package com.rogoz208.hometask4_2_fragments.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.hometask4_2_fragments.presentation.edit_user_screen.EditUserFragment
import com.rogoz208.hometask4_2_fragments.R
import com.rogoz208.hometask4_2_fragments.presentation.users_list_screen.UsersListFragment
import com.rogoz208.hometask4_2_fragments.databinding.ActivityMainBinding
import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main),
    UsersListFragment.EditUserClickListener, EditUserFragment.SaveUserButtonClickListener {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitialFragment()
    }

    override fun onSaveUserButtonClicked() {
        supportFragmentManager.popBackStackImmediate()
    }

    override fun onEditUserClicked(user: UserEntity, position: Int) {
        supportFragmentManager.commit {
            val fragment = EditUserFragment.newInstance(user, position)
            replace(
                binding.fragmentsContainer.id,
                fragment,
                EditUserFragment.EDIT_USER_FRAGMENT_TAG
            )
            addToBackStack(EditUserFragment.EDIT_USER_FRAGMENT_TAG)
        }
    }

    private fun setInitialFragment() {
        if (supportFragmentManager
                .findFragmentByTag(UsersListFragment.USERS_LIST_FRAGMENT_TAG) == null
        ) {
            supportFragmentManager.commit {
                val fragment = UsersListFragment.newInstance()
                replace(
                    binding.fragmentsContainer.id,
                    fragment,
                    UsersListFragment.USERS_LIST_FRAGMENT_TAG
                )
            }
        }
    }
}