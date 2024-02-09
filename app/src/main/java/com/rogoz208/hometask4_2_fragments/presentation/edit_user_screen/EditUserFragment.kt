package com.rogoz208.hometask4_2_fragments.presentation.edit_user_screen

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.rogoz208.hometask4_2_fragments.R
import com.rogoz208.hometask4_2_fragments.databinding.FragmentEditUserBinding
import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditUserFragment : Fragment(R.layout.fragment_edit_user) {

    private val binding by viewBinding(FragmentEditUserBinding::bind)
    private val viewModel by viewModels<EditUserViewModel>()

    private var editingUser: UserEntity? = null
    private var editingUserPosition: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getExtras()
        editingUser?.let { fillViewsWithExtras(it) }
        initViewModel()
        setupListeners()
    }

    private fun getExtras() {
        arguments?.let {
            editingUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(USER_EXTRA_KEY, UserEntity::class.java)
            } else {
                it.getParcelable(USER_EXTRA_KEY)
            }
            editingUserPosition = it.getInt(USER_POSITION_EXTRA_KEY)
        }
    }

    private fun fillViewsWithExtras(user: UserEntity) {
        with(binding) {
            userAvatarImageView.load(user.avatarUrl) {
                transformations(CircleCropTransformation())
            }
            avatarUrlEditText.setText(user.avatarUrl)
            firstNameEditText.setText(user.firstName)
            lastNameEditText.setText(user.lastName)
            phoneNumberEditText.setText(user.phoneNumber)
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            userSavedLiveData.observe(viewLifecycleOwner) { userSaved ->
                if (userSaved) {
                    setFragmentResult(REQUEST_KEY, bundleOf(SUCCESS_EXTRA_KEY to userSaved))
                    (requireActivity() as? SaveUserButtonClickListener)?.onSaveUserButtonClicked()
                }
            }
            avatarUrlLiveData.observe(viewLifecycleOwner) { avatarUrl ->
                binding.userAvatarImageView.load(avatarUrl) {
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            saveButton.setOnClickListener {
                val avatarUrl = avatarUrlEditText.text.toString()
                val firstName = firstNameEditText.text.toString()
                val lastName = lastNameEditText.text.toString()
                val phoneNumber = phoneNumberEditText.text.toString()
                val user = editingUser?.copy(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber,
                    avatarUrl = avatarUrl
                )
                viewModel.onUserSaved(user?.uId!!, user, editingUserPosition!!)
            }
            avatarUrlEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                    // Do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.onAvatarUrlChange(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                    // Do nothing
                }

            })
        }
    }

    interface SaveUserButtonClickListener {
        fun onSaveUserButtonClicked()
    }

    companion object {
        const val EDIT_USER_FRAGMENT_TAG = "EDIT_USER_FRAGMENT_TAG"
        const val REQUEST_KEY = "REQUEST_KEY"
        const val SUCCESS_EXTRA_KEY = "SUCCESS_EXTRA_KEY"

        private const val USER_EXTRA_KEY = "USER_EXTRA_KEY"
        private const val USER_POSITION_EXTRA_KEY = "USER_POSITION_EXTRA_KEY"

        fun newInstance(user: UserEntity, position: Int) = EditUserFragment().apply {
            arguments = bundleOf(USER_EXTRA_KEY to user, USER_POSITION_EXTRA_KEY to position)
        }
    }
}