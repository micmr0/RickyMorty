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
import com.uk.androidrecruitmentapp.data.local.Character
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment() {
    lateinit var charactersList: ListView

    companion object {
        fun newInstance(context: Context, character: Character?): CharactersFragment {
            val charactersFragment = CharactersFragment()

            val bundle = Bundle()
            bundle.putParcelable(context.getString(R.string.CHARACTERS_EXTRA), character)
            charactersFragment.arguments = bundle

            return charactersFragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersList = view.findViewById(R.id.characters_list)

        val character = requireArguments().get(getString(R.string.CHARACTERS_EXTRA)) as Character?
        val charactersNames: ArrayList<String> = ArrayList()
        for (result in character?.results!!) {
            charactersNames.add(result.name!!)
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, charactersNames)
        charactersList.adapter = adapter

        charactersList.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {}

            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (charactersList.adapter == null)
                    return

                if (charactersList.adapter.count == 0) return

                val l = visibleItemCount + firstVisibleItem
                if (l >= totalItemCount) {
                    progress.visibility = View.GONE
                }
            }
        })
    }
}
