import java.util.*
import java.util.*

class Test{
  public static long[] b;
  
  public static long solve(long[] a, int low, int high) {
    if (low == high) return 0;

    int mid = (low + high) / 2;
    long ansLeft = solve(a, low, mid);
    long ansRight = solve(a, mid + 1, high);
    long ansCross = 0;

    int left = low, right = mid + 1, itr = low;

    while(left <= mid && right <= high) {
      if(a[left] > a[right]){
        b[itr] = a[right];
        ++itr;
        ++right;
      } else{
        b[itr] = a[left];
        ++itr;
        ++left;
      }
    }

    while(left <= mid) {
      b[itr[ = a[left];
      ++itr;
      
    }
        
    return ansLeft + ansRight + ansCross;
  }
  
  public static void main(String[] args) {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    long n = Integer.parseInt(st.nextToken());
    long[] a = new long[100008];
    b = new long[100008];
    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < n; ++i) a[i] = Long.parseLong(st.nextToken());

    long ans = solve(a, 0, n-1);
    
    pw.println(ans);
    for(int i = 0; i < n; i++) pw.print(a[i] + " ");
    pw.flush();
  }
}
