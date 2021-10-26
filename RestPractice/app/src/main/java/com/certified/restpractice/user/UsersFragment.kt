/*
 * Copyright (c) 2021 Samson Achiaga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.certified.restpractice.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.certified.restpractice.adapters.UserAdapter
import com.certified.restpractice.databinding.FragmentUsersBinding
import com.certified.restpractice.model.User

class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private lateinit var users: List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = UserViewModelFactory()
        val viewModel: UserViewModel by lazy {
            ViewModelProvider(viewModelStore, viewModelFactory).get(UserViewModel::class.java)
        }
        viewModel.users.observe(viewLifecycleOwner) {
            users = it
        }
        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            binding.apply {
                if (it) progressBar.visibility = View.VISIBLE
                else progressBar.visibility = View.GONE
            }
        }
        viewModel.showToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Unable to load users", Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = UserAdapter()
        binding.recyclerViewUsers.adapter = adapter
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(requireContext())
    }
}