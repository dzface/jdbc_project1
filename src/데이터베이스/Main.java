package 데이터베이스;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Scanner;

public class Main {
    static String id;
    static String pw;
    static String myId;
    static String myNickName;

    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        String title = "영양제 추천 사이트";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("┌" + "- ".repeat(title.length()) + "-" + "┐");
            System.out.println("│   " + title + "   │");
            System.out.println("└" + "- ".repeat(title.length()) + "-" + "┘");
            System.out.println("[1]로그인 [2]회원가입 [3]종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    LogInDAO logIn = new LogInDAO();
                    System.out.println("로그인을 위해 아이디와 비밀번호를 입력 하세요.");
                    System.out.print("아이디 : ");
                    id = sc.next().trim();
                    System.out.print("비밀번호 : ");
                    pw = sc.next().trim();
                    if (!logIn.logIn(id, pw)) continue;
                    myId = id;
                    myNickName = LogInDAO.nickName;
                    break;
                case 2:
                    RegisterDAO register = new RegisterDAO();
                    System.out.println("회원가입 정보를 입력 하세요.");
                    System.out.print("이름 : ");
                    String name = sc.next().trim();
                    while (true) {
                        System.out.print("아이디 : ");
                        String id = sc.next().trim();
                        if(!register.checkId(id)) continue;
                        break;
                    }
                    while (true) {
                        System.out.print("비밀번호 : ");
                        String pw = sc.next().trim();
                        if (pw.length() <= 3) {
                            System.out.println("비밀번호는 4자 이상이어야 합니다.");
                            continue;
                        }
                        break;
                    }
                    System.out.print("닉네임 : ");
                    String nickName = sc.next().trim();
                    register.memberInsert(id,pw,name,nickName);
                    break;
                case 3:
                    System.out.println("메뉴를 종료 합니다");
                    return;
                default: break;
            }

            if (myId != null) {
                mainSelect();
            }
        }
    }
    public static void mainSelect() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("┌" + "- ".repeat(7) + "-" + "┐");
            System.out.println("│    메인페이지   │");
            System.out.println("└" + "- ".repeat(7) + "-" + "┘");
            System.out.println("[1]마이페이지 [2]검색 [3]로그아웃");
            int sel1 = sc.nextInt();
            switch (sel1) {
                case 1: if(!myPageSelect()) return;
                    break;
                case 2:
                    SearchDAO search = new SearchDAO();
                    BoardDAO board = new BoardDAO();
                    TreeSet<NutrientsVO> set = null;
                    int sel2;
                    while (true) {
                        System.out.println("[1]성분 [2]효능 [3]돌아가기");
                        sel2 = sc.nextInt();
                        switch (sel2) {
                            case 1:
                                System.out.print("성분을 입력하세요 : ");
                                String ingredent = sc.next().trim();
                                set = search.search(sel2,ingredent);
                                if(set.isEmpty()) continue;
                                break;
                            case 2:
                                System.out.print("효능을 입력하세요 : ");
                                String efficacy = sc.next().trim();
                                set = search.search(sel2,efficacy);
                                if(set.isEmpty()) continue;
                            case 3:
                                break;
                            default:
                        }
                        break;
                    }
                    if(set == null) break;
                    search.printNutrients(set);
                    String str = null;
                    while (true) {
                        System.out.print("영양제 이름을 입력하세요 : ");
                        sc.nextLine();
                        String name1 = sc.nextLine().trim();
                        if (name1.isEmpty()) {System.out.println("이름을 입력하세요.");continue;}
                        str = board.checkNut(name1);
                        if(str == null) continue;
                        str = str.replace("[","").replace("]","");
                        break;
                    }
                    NutrientsVO voN = board.boardNut(str);
                    HashSet<String> set1 = board.boardEFF(str);
                    List<BoardVO> list= board.boardList(str);
                    board.printBoard(voN,set1,list);
                    while (true) {
                        if(board.searchBoard(str)) {
                            System.out.println("[1]댓글쓰기, [2]돌아가기");
                            int sel3 = sc.nextInt();
                            sc.nextLine();
                            switch (sel3) {
                                case 1:
                                    while (true) {
                                        System.out.print("댓글 : ");
                                        String content = sc.nextLine().trim();
                                        if (content.isEmpty()) {
                                            System.out.println("내용을 입력하세요.");
                                            continue;
                                        }
                                        board.comment(voN, content);
                                        break;
                                    }
                                    break;
                                case 2:
                                    break;
                                default:
                                    continue;
                            }
                        }
                        else System.out.println("[1]돌아가기");
                        int sel3 = sc.nextInt();
                        break;
                    }
                    break;
                case 3:
                    System.out.println("로그아웃 합니다");
                    return;
                default:
            }
        }
    }
    public static boolean myPageSelect(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("┌" + "- ".repeat(myNickName.length() + 7) + "-" + "┐");
            System.out.println("│    " + myNickName + "님의 페이지   │");
            System.out.println("└" + "- ".repeat(myNickName.length() + 7) + "-" + "┘");
            System.out.println("[1]내정보 [2]작성한 댓글 [3]돌아가기");
            int sel4 = sc.nextInt();
            switch (sel4) {
                case 1: // 내정보
                    MyInfoDAO myInfo = new MyInfoDAO();
                    MemberVO vo = myInfo.myInfo();
                    myInfo.printMyInfo(vo);
                    while (true) {
                        System.out.println("[1]수정 [2]회원탈퇴 [3]돌아가기");
                        int sel5 = sc.nextInt();
                        switch (sel5) {
                            case 1: // 수정
                                System.out.println("[1]이름, [2]비밀번호,[3]닉네임,[4]돌아가기");
                                int sel6 = sc.nextInt();
                                switch (sel6) {
                                    case 1:
                                        System.out.print("이름 : ");
                                        String name2 = sc.next().trim();
                                        myInfo.updateMyInfo(sel6, name2);
                                        break;
                                    case 2:
                                        while (true) {
                                            System.out.print("비밀번호 : ");
                                            String pw = sc.next().trim();
                                            if (pw.length() <= 3) {
                                                System.out.println("비밀번호는 4자 이상이어야 합니다.");
                                                continue;
                                            }
                                            myInfo.updateMyInfo(sel6, pw);
                                            break;
                                        }
                                        break;
                                    case 3:
                                        System.out.print("닉네임 : ");
                                        String nickName = sc.next().trim();
                                        myInfo.updateMyInfo(sel6, nickName);
                                        Main.myNickName = nickName;
                                        break;
                                }
                                break;
                            case 2: // 탈퇴
                                System.out.println("정말 회원탈퇴 하시겠습니까?");
                                System.out.println("[1]회원탈퇴 [2]돌아가기");
                                int sel7 = sc.nextInt();
                                switch (sel7) {
                                    case 1: myInfo.deleteMyInfo();
                                        Main.myId = null;
                                        Main.myNickName = null;
                                        return false;
                                    case 2:
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 3: // 돌아가기
                                break;
                            default:
                                continue;
                        }
                        break;
                    }
                    continue;
                case 2: // 작성한 댓글
                    BoardDAO board = new BoardDAO();
                    List<BoardVO> list= board.searchBoard();
                    board.printMyContent(list);
                    if(list.isEmpty()) {System.out.println("작성한 댓글이 없습니다."); break;}
                    while (true) {
                        System.out.println("[1]수정 [2]삭제 [3]돌아가기");
                        int sel8 = sc.nextInt();
                        switch (sel8) {
                            case 1: // 수정
                                System.out.print("수정할 글번호를 입력하세요 : ");
                                int num = sc.nextInt();
                                if (!board.checkMyContent(num)) break;
                                while (true) {
                                    System.out.print("수정할 내용 : ");
                                    String content = sc.nextLine().trim();
                                    if (content.isEmpty()) {
                                        System.out.println("내용을 입력하세요");
                                        continue;
                                    }
                                    board.updateContent(num,content);
                                    break;
                                }
                                break;
                            case 2: // 삭제
                                System.out.print("삭제할 글번호를 입력하세요 : ");
                                int num1 = sc.nextInt();
                                if (!board.checkMyContent(num1)) break;
                                board.deleteContent(num1);
                                break;
                            case 3: break;
                            default:
                                continue;
                        }
                        break;
                    }
                    continue;
                case 3:
                    break;
                default:
            }
            break;
        }
        return true;
    }
}