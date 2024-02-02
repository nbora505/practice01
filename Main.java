import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos{
    int x,y,t;
    Pos(int x,int y, int t){
       this.x = x; this.y = y; this.t=t;
    }
}

public class Main {
    static int ans = Integer.MAX_VALUE;
    public static int sti(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = sti(st), M = sti(st), T = sti(st);
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = sti(st);
            }
        }

        int[] dy = {1,-1,0,0};
        int[] dx = {0,0,1,-1};
        // Queue<Pos> q = new LinkedList<>();
        // 유의미한 차이는 없지만 일반적으로 더 빠르게 add, remove 가능
        ArrayDeque<Pos> q = new ArrayDeque<>();
        q.add(new Pos(0,0,0));
        while(!q.isEmpty()){
            Pos cur = q.pollFirst();
            int x = cur.x, y = cur.y, t = cur.t;
            if(map[y][x]==2)
                ans = Math.min((N-1-y)+(M-1-x)+t,ans);
            if(y==N-1&&x==M-1)
                ans = Math.min(ans,t);
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N ||
                        map[ny][nx] == 1 || visited[ny][nx])
                    continue;
                visited[ny][nx] = true;
                q.add(new Pos(nx, ny, t + 1));
            }
        }
        System.out.println(ans>T ? "Fail":ans);
    }
}