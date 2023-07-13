import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

//---------------게시글 클래스 생성
class Post { 
	//게시글에 필요한 변수들 선언
    private String title; //제목
    private String content; //내용
    private String author; //작성자
    private String date; //작성일
 
    public Post(String title, String content, String author) { //속성들 초기화
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = getCurrentDate(); 
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    String getDate() {
    	
        return date; 
    }
    
    private String getCurrentDate() { //현재 날짜 가져오는 코드
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //날짜형식지정
        String formattedDate = sdf.format(now); //날짜를 문자열로 변환
       
        return formattedDate;  //변환된 날짜를 문자열로 반환
    }
}

//----------------게시물 관리 클래스
class Board { 
    private ArrayList<Post> posts; //posts라는 리스트에 Post(게시글)저장

    public Board() {
        posts = new ArrayList<>(); //게시글들을 리스트로 저장하는 posts 
    }

    public ArrayList<Post> getPosts() { //getPosts 리스트에 posts(게시글목록)저장=목록열람
        return posts;
    }

    public void addPost(Post post) { //Post객체를 posts에 추가 =새 게시글 등록할 때
        posts.add(post);
    }

    public boolean deletePost(int postIndex) {  //인덱스 기준 게시물을 삭제하니까 유효범위 지정해주기
        if (postIndex >= 0 && postIndex < posts.size()) { //0보다 크거나 같고, 게시글 목록 사이즈보다 작으면
            posts.remove(postIndex); //지울 수 있음 =게시글 삭제할 때
            return true;
        }
        return false;
    }

    public Post getPost(int postIndex) { //인덱스 기준 해당 인덱스의 게시물 반환,유효범위 지정
        if (postIndex >= 0 && postIndex < posts.size()) {//0보다 크고 게시글목록 사이즈보다 작으면
            return posts.get(postIndex); //게시글보여주기
        }
        return null;
    }
}

public class Chat1 {
    private static Board board;
    private static Scanner scanner;

 
//---------선택지 목록
    private static void showMenu() {
        int choice = 0; //사용자 선택 초기화
       
        while (choice != 5) { //5가 될 때 까지 반복
        	System.out.println("=====================");
        	System.out.println(" 번호   제목    작성자    작성일");
        	System.out.println("---------------------");

            System.out.print("1. 목록  ");
            System.out.print("2. 등록  ");
            System.out.print("3. 삭제  ");
            System.out.print("4. 내용  ");
            System.out.println("5. 종료  ");
            System.out.print("선택: ");

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    displayPosts(); //목록보기
                    break;
                case 2:
                    createPost(); //게시글 등록
                    break;
                case 3:
                    deletePost(); //게시글 삭제
                    break;
                case 4:
                    viewPost(); //게시글 내용 보기
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다."); //종료하기
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    //-------게시글 목록 보기
    private static void displayPosts() {
        ArrayList<Post> posts = board.getPosts(); //board클래스의 getPosts(게시글목록)을 호출
        if (posts.isEmpty()) {
            System.out.println("게시물이 없습니다.");
        } else {
            for (int i = 0; i < posts.size(); i++) { //게시물목록 사이즈만큼 반복
                Post post = posts.get(i); //i번째 게시물 가져오기
                System.out.print("  게시물 번호: " + (i+1));
                System.out.print("  제목: " + post.getTitle());
                System.out.print("  작성자: " + post.getAuthor());
                System.out.print("  작성일: " + post.getDate());
                System.out.println();
            }
        }
    }
    //-----------게시물 등록하기
    private static void createPost() {
        System.out.print("제목> ");
        String title = scanner.nextLine();
        System.out.print("내용>");
        String content = scanner.nextLine();
        System.out.print("작성자> ");
        String author = scanner.nextLine(); //사용자에게 입력 받은 후
       

        Post post = new Post(title, content, author);
        board.addPost(post);

   
        System.out.println("게시물이 작성되었습니다.");
    }
    
    //-------게시물 삭제
    private static void deletePost() {
        System.out.print("삭제할 게시물 번호: ");
        int postIndex = scanner.nextInt(); //사용자 입력 번호를 postIndex에 저장
        scanner.nextLine(); 

        if (board.deletePost(postIndex)) { //유효범위에 해당하는지 값넣어 확인
            System.out.println("게시물이 삭제되었습니다.");
        } else {
            System.out.println("존재하지 않는 게시물 번호입니다.");
        }
    }
//게시물 내용 보기
    private static void viewPost() {
        System.out.print("게시물 번호: ");
        int postIndex = scanner.nextInt(); //사용자 입력 번호를 postIndex에 저장
        scanner.nextLine(); // 버퍼 비우기

        Post post = board.getPost(postIndex); //유효범위에 해당하면
        if (post != null) { 
            System.out.print("  제목: " + post.getTitle());
            System.out.print("  내용: " + post.getContent());
            System.out.print("  작성자: " + post.getAuthor());
            System.out.print("  작성일: " + post.getDate());
        } else {
            System.out.println("존재하지 않는 게시물 번호입니다.");
        }
    }
    
    //---메인함수
    public static void main(String[] args) {
        board = new Board();
        scanner = new Scanner(System.in);

        showMenu(); //메뉴표시, 동작실행
    }
    
}





