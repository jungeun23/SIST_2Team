# 그룹웨어
## SIST Java console Project

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

그룹웨어를 통한 업무공유, 전자문서, 사내 이메일 등을 통해서 업무 시간을 단축하고, 경제적으로 업무를 진행하기 위해 제작한 프로그램. 인사 관리에 필요한 요구사항을 구현하기 위하여 직급과 부서를 나누고, 분산된 업무를 하나로 관리할 수 있는 통합 프로그램이다.

- 종이 문서가 없는 사무실 구현에 필요한 다양한 기능을 제공하여, 구축 시 최대한의 효율을 얻는 것을 목표로 개발한다. 시중의 일반적인 그룹웨어는 메신저를 통해 단순 의사소통을 지원하는 경우가 대부분이어서 활용도가 떨어진다. 그룹웨어를 도입한 기업들이 그룹웨어의 활용성에 회의적이라는 기사를 확인하고, 활용도를 높이기 위해 최대한 많은 기능을 구현하였다. 협업 업무가 가능하다는 그룹웨어의 가장 큰 장점을 살려서 그룹웨어 시스템을 구축하는 것을 목표로 한다.
- 5명 구성원 1개월 작업.

## Features
1. 메일
이메일의 읽기, 쓰기, 검색, 수정, 스팸확인 기능
2. 메신저
메신저의 읽기, 쓰기, 삭제, 수정 기능
3. 주소록
주소록을 통한 검색 및 추가, 삭제 기능
1. 전자결재
전자결재의 읽기, 쓰기, 삭제, 수정, 기능
1. 게시판
게시글의 읽기, 쓰기, 삭제, 수정 기능
1. 일정관리 
일정의 읽기, 쓰기, 삭제, 수정 기능
2. 휴가관리
휴가 신청, 취소 및 신청 내역 확인 기능 
3. 예약 /대여
회의실 예약, 예약 취소, 예약 현황 확인 기능
차량 재고 관리를 바탕으로 차량 예약 등록, 확인, 삭제 기능 
4. 교육 센터
교육 일정 신청, 조회, 수정, 삭제 기능 
1. 근태관리
출근 및 퇴근 등록, 본인의 월간 근무시간 조회, 직원 별 월간 근무시간 조회
2. 인사관리
권한 있는 사용자가 직원들의 부서, 직급을 변경하거나, 신입사원, 퇴사직원 관리, 고과 데이터를 관리하는 기능
3. 수당관리
로그인한 유저의 성과급, 연장근무 수당 조회 기능
권한 있는 사용자의 부서별/직원 별 연장근무 수당, 성과급, 월급 검색 조회 기능



## Tech

- Java
    - 파일 입출력
    BufferedReader, Writer(new FileReader, Writer( "path" )
    컬렉션
    ArrayList<T>, LinkedList<T>, HashMap<T>, Comparator 등
    오버로딩, 오버라이딩
    생성자 오버로딩, 객체에 속한 ArrayList 함수 오버로딩, 비교 연산을 위한 오버로딩, Calendar 출력을 위한 오버라이딩
    기타 레퍼런스 타입 클래스 
        1. 데이터를 생성하기 위한 Random, Math클래스
        2.  일정 조작을 위한 Calendar 클래스를 이용


    

## Review
DB를 통해 분산 처리 시스템을 구현하였다. 가상 머신에서 돌아갈 수 있도록 처음 세팅하는것이 힘들었지만 한번 설정 된 후에는 큰 어려움 없이 진행 되었다. SQL문을 이용하여 단어의 counting을 진행하였고 jsoup으로 크롤링 및  구문 분석을 할 수 있었다. 이 라이브러리가 없었으면 수작업으로 단어를 추출했어야 했는데, 큰 어려움 없이 프로젝트를 진행 할 수 있었다. 분산 처리에는 한계가 있었다. 너무 많은 웹사이트를 크롤링 할 시에는 VM에서 동작중인 DB가 뻗는 경우가 있었다. 때문에 slave에서 일정 작업량 이상에서는 DB가 열심히 일을 하고 있을때 master에서 계속해서 작업을 넘기지 않고 stop-and-wait할 수 있는 조건을 달아주어야 프로그램이 죽지 않고 가용성을 유지할 수 있었다. 분산 처리 개수가 많아 질수록 당연히 처리속도가 빨라졌다 물론 1/N은 아니지만 꽤 효율적인 결과를 볼 수 있었다. 

## Result
![result](reuslt1.png)
![result](reuslt2.png)
![result](reuslt3.png)

## License

MIT



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
