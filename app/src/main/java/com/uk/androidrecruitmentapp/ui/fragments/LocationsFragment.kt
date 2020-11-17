package com.uk.androidrecruitmentapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.data.local.Location
import kotlinx.android.synthetic.main.fragment_characters.*

open class LocationsFragment : Fragment() {
    lateinit var locationsList: ListView

    companion object {
        fun newInstance(context: Context, location: Location): LocationsFragment {
            val locationsFragment = LocationsFragment()

            val bundle = Bundle()
            bundle.putParcelable(context.getString(R.string.LOCATIONS_EXTRA), location)
            locationsFragment.arguments = bundle

            return locationsFragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationsList = view.findViewById(R.id.locations_list)

        val locations = requireArguments().get(context?.getString(R.string.LOCATIONS_EXTRA)) as Location?
        val locationsNames: ArrayList<String> = ArrayList()
        for (result in locations!!.results!!) {
            locationsNames.add(result.name!!)
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, locationsNames)
        locationsList.adapter = adapter

        locationsList.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {}

            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (locationsList.adapter == null)
                    return

                if (locationsList.adapter.count == 0) return

                val l = visibleItemCount + firstVisibleItem
                if (l >= totalItemCount) {
                    progress.visibility = View.GONE
                }
            }
        })
    }
}
