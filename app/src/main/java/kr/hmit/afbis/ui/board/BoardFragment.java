package kr.hmit.afbis.ui.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.hmit.afbis.R;
import kr.hmit.base.base_fragment.BaseFragment;

// 테스트 커밋
public class BoardFragment extends BaseFragment {
    public BoardFragment() {
        // Required empty public constructor
    }

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_board, container, false);

        return view;
    }
}
