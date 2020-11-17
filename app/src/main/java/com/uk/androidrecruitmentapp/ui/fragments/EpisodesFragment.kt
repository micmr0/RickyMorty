package com.uk.androidrecruitmentapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.data.local.Episode
import kotlinx.android.synthetic.main.fragment_characters.*

class EpisodesFragment : Fragment(R.layout.fragment_episodes) {
    private lateinit var episodesList: ListView


    companion object {
        fun newInstance(context : Context, episode: Episode?): EpisodesFragment {
            val episodesFragment = EpisodesFragment()

            val bundle = Bundle()
            bundle.putParcelable(context.getString(R.string.EPISODES_EXTRA), episode)
            episodesFragment.arguments = bundle

            return episodesFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodesList = view.findViewById(R.id.episodes_list)

        val episodes = requireArguments().get(context?.getString(R.string.EPISODES_EXTRA)) as Episode?
        val episodesNames: ArrayList<String> = ArrayList()
        for (result in episodes!!.results) {
            episodesNames.add(result.name!!)
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, episodesNames)
        episodesList.adapter = adapter

        episodesList.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {}

            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (episodesList.adapter == null)
                    return

                if (episodesList.adapter.count == 0) return

                val l = visibleItemCount + firstVisibleItem
                if (l >= totalItemCount) {
                    progress.visibility = View.GONE
                }
            }
        })
    }
}
