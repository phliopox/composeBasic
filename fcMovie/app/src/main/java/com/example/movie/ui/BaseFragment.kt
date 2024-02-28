package com.example.movie.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.movie.features.common.viewmodel.ThemeViewModel

open class BaseFragment : Fragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}
