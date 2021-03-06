package com.coconutlab.app.course1_2.course1_2_1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.coconutlab.app.R;
import com.coconutlab.app.helper.AnswerManager;
import com.coconutlab.app.helper.ContentPagerListener;
import com.coconutlab.app.helper.PageHelper;
import com.coconutlab.app.helper.ViewFactoryCS;
import com.coconutlab.app.helper.WidgetSet;

import java.util.ArrayList;

/**
 * course 1-2 연산자
 * step 2 : 연산자 종류 문제 풀이
 */

public class Course1_2_1Step2 extends Fragment{

    View root;
    ViewFactoryCS viewFactory;
    ContentPagerListener contentPagerListener;

    public Course1_2_1Step2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step2, container, false);
        return root;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step2);
        viewFactory = new ViewFactoryCS(layout);

        //문제 카드 생성
        LinearLayout questionCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("Q. 다음 식을 보고 빈칸에 적절한 연산자를 넣으시오. ", 20, questionCard);

        //사용자 입력 카드 생성
        TableLayout userInputCard = viewFactory.createTableCard(1.0f, Color.WHITE, new int[]{0,0,0,0});
        //보기 블록 카드 생성
        HorizontalScrollView scrollView = viewFactory.createHorizontalScrollViewCard(0.0f, Color.WHITE, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.addQuestion("1. 4 [[+]] 5 = 9", 20, userInputCard);
        viewFactory.addQuestion("2. 6 [[*]] 2 = 12", 20, userInputCard);
        viewFactory.addQuestion("3. 72 [[-]] 44 = 28", 20, userInputCard);
        viewFactory.addQuestion("4. 22 [[/]] 2 = 11", 20, userInputCard);
        viewFactory.addQuestion("5. 6 [[==]] 6 = True", 20, userInputCard);
        viewFactory.createBlocks(new String[]{"+", "-", "*", "/", "%", "==", ";"}, scrollView, userInputCard, 2);

//        //보기 블록 카드 생성
//        HorizontalScrollView scrollView = viewFactory.createHorizontalScrollViewCard(0.0f, Color.WHITE, new int[]{0,0,0,PageHelper.defaultMargin});
        //viewFactory.createBlocks(new String[]{"+", "-", "*", "/", "%", "==", ";"}, scrollView, userInputCard, 2);

        final TextView feedBackTextContainer = viewFactory.createFeedBackCard(0.5f, new int[]{0,0,0,0});
        viewFactory.addFeedBackText(0, "블록을 터치해서 알맞은 연산자를 넣어주세요! 잘못 입력한 값은 빈칸을 터치하면 취소됩니다", feedBackTextContainer);

        final LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,0});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);



        //answercheckwithadd 동적으로 인플레이트
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.answercheck, linearLayout);

        viewFactory.addSpace(0.3f);

        /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
//        goNext.setOnClickListener(new ContentPageListener(5, getActivity()));
//        goPrev.setOnClickListener(new ContentPageListener(4, getActivity()));
        contentPagerListener = new ContentPagerListener(getActivity());
        goNext.setOnClickListener(contentPagerListener);
        goPrev.setOnClickListener(contentPagerListener);


        ImageButton buttonRefresh = (ImageButton)root.findViewById(R.id.button_delete);
        ImageButton buttonCompile = (ImageButton)root.findViewById(R.id.button_compile);

        //새로고침 버튼 누르면 editText 모두 제거
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                ArrayList<TextView> textViews = widgetSet.getTextView();
                for(TextView textView : textViews){
                    //초기화시킨다
                    textView.setText("          ");
                    //remainlist 에 추가한다
                    viewFactory.sortByTag(textView);
                }

            }
        });

        //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCorrect()){
                    viewFactory.addFeedBackText(1, "축하합니다~ 성공!", feedBackTextContainer);
                    contentPagerListener.setIsSolved(true);
                }
                else{
                    new AnswerManager(getContext()).vibrate();
                    viewFactory.addFeedBackText(2, "다시 한 번 확인해주세요!", feedBackTextContainer);
                }
            }
        });

    }

    public boolean isCorrect(){
        WidgetSet widgetSet = viewFactory.getWidgetSet();
        //radiogroup 은 하나밖에 없음
        ArrayList<TextView> answerBlocks = widgetSet.getTextView();
        String str = "";
        //getAlltext
        for(TextView block : answerBlocks){
            str += block.getText();
        }
        Log.e("gary", "answerblocks : " + str);
        if(str.equals("+*-/==")){
            return true;
        }
        return false;
    }



}
