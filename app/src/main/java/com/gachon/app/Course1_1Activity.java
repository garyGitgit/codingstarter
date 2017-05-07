package com.gachon.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 일정한 포맷을 만들고 포맷에 따라 간편하게 문제를 만들 수 있는 ViewFactoryCS 클래스를 만들 예정
 *
 * step1 비유) 이미지 or 애니메이션 + 글씨 설명(scrollview + linearlayout)
 * step2 개념) 이미지 or 애니메이션 + 글씨 설명(scrollview + linearlayout)
 * step3 체험) 원하는 뷰를 ViewFactoryCS 를 통해서 생성하여 차곡차곡 벽돌쌓기 하듯이 쌓는다.
 * 이에 따른 결과값도 한 페이지 안에서 보여준다. 공간이 모자라면 스크롤뷰로 내린다
 * step4 문제) 문제 섹션(이미지 or 애니메이션) + 문제 출제(이건 보통 코드 빈칸 문제가 나오는데 \n 이나 _____
 * 와 같은 delimiter 를 이용한다
 *

 **/

public class Course1_1Activity extends AppCompatActivity implements OnGoNextPageInterface{
    MyViewPager viewPager;
    //static int courseStepNum = 5;
    static int courseStepNum = 1;
    ImageView[] progressImageViewList;

    ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1_1);


        //progress 상태 표시
        progressImageViewList = new ImageView[5];
        progressImageViewList[0] = (ImageView)findViewById(R.id.course_progress1);
        progressImageViewList[1] = (ImageView)findViewById(R.id.course_progress2);
        progressImageViewList[2] = (ImageView)findViewById(R.id.course_progress3);
        progressImageViewList[3] = (ImageView)findViewById(R.id.course_progress4);
        progressImageViewList[4] = (ImageView)findViewById(R.id.course_progress5);

        //초기 시작은 첫번째 progress 이미지 초기화
        progressImageViewList[0].setBackgroundColor(Color.GREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (MyViewPager) findViewById(R.id.course1_1_ViewPager);
        //동적으로 생성된 fragment 들의 container 는 viewpager 임
        container = viewPager;
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        viewPager.setPagingEnabled(false);

        //여기까지는 허용 가능
    }

    public void onProgressImageClickListener (View v){
        int id = v.getId();
        int index = 0;
        switch (id){
            case R.id.course_progress1:
                viewPager.setCurrentItem(0);
                index = 0;
                break;
            case R.id.course_progress2:
                viewPager.setCurrentItem(1);
                index = 1;
                break;
            case R.id.course_progress3:
                viewPager.setCurrentItem(2);
                index = 2;
                break;
            case R.id.course_progress4:
                viewPager.setCurrentItem(3);
                index = 3;
                break;
            case R.id.course_progress5:
                viewPager.setCurrentItem(4);
                index = 4;
                break;
        }
        setProgressColor(index);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                //이 부분이 dynamic 하게 fragment 를 생성을 해야함
                case 0:
                    return new Course1_1Step1Fragment();
                case 1:
                    return new Course1_1Step1Fragment();
                case 2:
                    return new Course1_1Step1Fragment();
                case 3:
                    return new Course1_1Step1Fragment();
                case 4:
                    return new Course1_1Step1Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return courseStepNum;
        }
    }

    public void setProgressColor(int finish){
        for(int i = 0; i <= finish; i++){
            progressImageViewList[i].setBackgroundColor(Color.GREEN);
        }
        for(int i = finish+1; i < courseStepNum; i++){
            progressImageViewList[i].setBackgroundColor(Color.WHITE);
        }


    }

    //프래그먼트에서 발생한 다음으로 가기 버튼 이벤트 처리
    @Override
    public void onPressGoNext() {
        int thisPage = viewPager.getCurrentItem();

        if (thisPage < 4) {
            Toast.makeText(Course1_1Activity.this, "성공!", Toast.LENGTH_SHORT).show();
            viewPager.setCurrentItem(++thisPage);
            //지금 페이지 번호에 맞게 progress 배경색을 색칠해준다. 추후에는 색깔을 칠하던가 색깔있는 아이콘을 쓰던가 해야지
            setProgressColor(thisPage);
        }
        else
            Toast.makeText(Course1_1Activity.this, "마지막 단계입니다", Toast.LENGTH_SHORT).show();

    }

}