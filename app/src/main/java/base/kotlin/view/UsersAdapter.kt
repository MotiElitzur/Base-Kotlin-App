package base.kotlin.view

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

import base.kotlin.databinding.UserViewBinding
import base.kotlin.model.User
import base.kotlin.viewModel.UsersViewModel

/**
 * This class contains the users recycler view.
 */
class UsersAdapter(usersViewModel: UsersViewModel) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    // region Data Members

    // The users view model.
    private val _usersViewModel: UsersViewModel = usersViewModel

    // The users list.
    private val _userList: MutableLiveData<MutableList<User>> = usersViewModel._usersList

    // endregion

    // region RecyclerView Adapter

    override fun getItemCount(): Int = if(_userList.value != null ) _userList.value?.size!! else 0;

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(_userList.value?.get(position), _usersViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        // Get the user view binding.
        val binding = UserViewBinding.inflate(LayoutInflater.from(parent.context))

        return UserViewHolder(binding)
    }

    // endregion

    // region User View Holder

    /**
     * The user view holder to bind the user in user view.
     */
    inner class UserViewHolder(val binding: UserViewBinding) : RecyclerView.ViewHolder(binding.root) {

        // region Public Methods

        /**
         * Receive a user and bind it to user view binding.
         *
         * @param user The user to bind.
         *
         * @param usersViewModel UsersViewModel, to do more action from users view binding.
         */
        fun bind(user: User?, usersViewModel: UsersViewModel?) {

            // bind the user.
            binding.user = user
            binding.userViewModel = usersViewModel

            binding.executePendingBindings()
        }

        // endregion
    }

    // endregion
}