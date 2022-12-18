package ru.kirillashikhmin.tauth.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.arrival.traceability2.core.ui.dialogs.DialogResolver
import kotlinx.coroutines.flow.Flow
import me.aartikov.sesame.property.PropertyObserver
import ru.kirillashikhmin.tauth.core.helpers.BackPressedInterceptor
import ru.kirillashikhmin.tauth.core.helpers.toMap
import ru.kirillashikhmin.tauth.core.helpers.tryGetValue
import ru.kirillashikhmin.tauth.core.internal.BaseViewModel


abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout), PropertyObserver,
    BackPressedInterceptor {

    abstract val vm: BaseViewModel
    override val propertyObserverLifecycleOwner: LifecycleOwner get() = viewLifecycleOwner
    private var dialogService: DialogResolver? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argumentsMap = arguments?.toMap()
        argumentsMap?.let {
            vm.navigationParams =
                it.tryGetValue<Bundle>(ArchConsts.navigation_properties)?.toMap() ?: mapOf()
            vm.resultKey = it.tryGetValue<String>(ArchConsts.navigation_result_key)
        }
        vm.onViewCreated()
        vm.dialog.showAlert bind {
            if (dialogService == null) dialogService =
                DialogResolver(requireContext(), childFragmentManager)
            dialogService?.show(it.first, it.second)
        }
    }

    override fun onBackPressed(): Boolean = vm.close(true)

    override fun onDestroyView() {
        super.onDestroyView()
        dialogService = null
        vm.viewClosed()
    }
}
