package kr.hmit.afbis.ui.drawer_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import kr.hmit.afbis.BuildConfig;
import kr.hmit.afbis.databinding.FragmentDrawerMenuBinding;
import kr.hmit.base.base_fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrawerMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawerMenuFragment extends BaseFragment {
    //====================================
    // final
    //====================================
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //====================================
    // view
    //====================================
    private FragmentDrawerMenuBinding binding;


    //====================================
    // variable
    //====================================
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<MenuVO> mList;
    private MenuAdapter mAdapter;

    //====================================
    // initialize
    //====================================
    public DrawerMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawerMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrawerMenuFragment newInstance(String param1, String param2) {
        DrawerMenuFragment fragment = new DrawerMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDrawerMenuBinding.inflate(getLayoutInflater(), container, false);

        initLayout();

        return binding.getRoot();
    }

    /**
     * 레이아웃 초기화
     */
    private void initLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        mList = new ArrayList<>();

        // 목록
        mList.add(new MenuVO("커뮤니티", new ArrayList<>(Arrays.asList(
                new SubMenuVO("업무관리", "00"),
                new SubMenuVO("일정관리", "01")
        ))));
        mList.add(new MenuVO("기준정보", new ArrayList<>(Arrays.asList(
                new SubMenuVO("거래처관리", "10"),
                new SubMenuVO("작업자코드", "11")
        ))));
        mList.add(new MenuVO("제품관리", new ArrayList<>(Arrays.asList(
                new SubMenuVO("제품정보", "20")
        ))));
        mList.add(new MenuVO("입고관리", new ArrayList<>(Arrays.asList(
                new SubMenuVO("발주관리", "30"),
                new SubMenuVO("입고대기", "31")
        ))));
        mList.add(new MenuVO("설비관리", new ArrayList<>(Arrays.asList(
                new SubMenuVO("설비정보", "40")
        ))));
        mList.add(new MenuVO("생산관리", new ArrayList<>(Arrays.asList(
                new SubMenuVO("생산계획", "50"),
                new SubMenuVO("제품실적", "51")
        ))));
        mList.add(new MenuVO("출고관리", new ArrayList<>(Arrays.asList(
                new SubMenuVO("주문접수", "60"),
                new SubMenuVO("출고대기", "61")
        ))));
        mList.add(new MenuVO("재고관리", new ArrayList<>(Arrays.asList(
                new SubMenuVO("재고목록", "70")
        ))));

//        mList.add(new MenuVO("품질관리", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));
//        mList.add(new MenuVO("농가관리", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));
//        mList.add(new MenuVO("택배관리", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));
//        mList.add(new MenuVO("고객관리", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));
//        mList.add(new MenuVO("인증관리", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));
//        mList.add(new MenuVO("프로젝트 관리", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));
//        mList.add(new MenuVO("지원사업", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));
//        mList.add(new MenuVO("홍보/마케팅", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));
//        mList.add(new MenuVO("통계분석", new ArrayList<>(Arrays.asList("제품정보", "BOM관리", "단가이력", "전용품목"))));

        mAdapter = new MenuAdapter(mContext, mList);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
