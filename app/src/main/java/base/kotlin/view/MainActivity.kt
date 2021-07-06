package base.kotlin.view

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil

import base.kotlin.R
import base.kotlin.databinding.ActivityMainBinding
import base.kotlin.viewModel.UsersViewModel

/**
 * The main app activity with the users list.
 */
class MainActivity : AppCompatActivity() {

    // region Data Members

    // The main activity binding.
    private var _mainBinding: ActivityMainBinding? = null

    // The users adapter.
    private var _usersAdapter: UsersAdapter? = null

    // endregion

    // region Life Cycle Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the main binding from main layout.
        _mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize the activity.
        initUsers()
    }

    // endregion

    // region Private Methods

    /**
     * Initialize the activity with the view model.
     */
    private fun initUsers() {

        val usersViewModel = UsersViewModel(this)

        // Set the view model to the adapter.
        _usersAdapter = UsersAdapter(usersViewModel)

        // Add the users adapter to the main activity.
        _mainBinding?.usersAdapter = _usersAdapter

        // Start listening when the user live list updated.
        usersViewModel._usersList.observe(this,
            {
                // Notify the users adapter that the users list updated.
                _usersAdapter!!.notifyDataSetChanged()
            })

        // Start to read the phone users.
        usersViewModel.startReadPhoneUser()
    }

    // endregion
}