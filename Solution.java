import java.util.*;
class Solution{
	static int st[];
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[n];
		for(int i = 0;i<n;i++)
			arr[i] = in.nextInt();
		buildSegmentTree(arr);
		int q = in.nextInt();
		
		while(q-->0){
			int qs = in.nextInt();
			int qe = in.nextInt();
			System.out.println(RMQ(arr,qs,qe));
		}
	}
	static void buildSegmentTree(int arr[]){
		
		int n = arr.length;
		int x = (int) Math.ceil(Math.log(n)/Math.log(2));
		int size = (int)Math.pow(2,x+1)-1;
		st = new int[size];
		buildSegmentTreeUtil(0,n-1,arr,0);
	}
	static int buildSegmentTreeUtil(int l,int r,int arr[],int i){
		if(l == r){
			st[i] = arr[l];
			return st[i]; 
		}
		int mid = (l+r)/2;
		st[i] = Math.min(buildSegmentTreeUtil(l,mid,arr,2*i+1),buildSegmentTreeUtil(mid+1,r,arr,2*i+2));
		return st[i];
	}
	static int RMQ(int arr[],int qs,int qe){
		int n = arr.length;
		return RMQUtil(arr,qs,qe,0,n-1,0);
	}
	static int RMQUtil(int arr[],int qs,int qe,int l,int r,int i){
		if(qs<=l && qe>=r)
			return st[i];
		if(qs>r || qe<l)
			return Integer.MAX_VALUE;
		int mid = (l+r)/2;
		return Math.min(RMQUtil(arr,qs,qe,l,mid,2*i+1),RMQUtil(arr,qs,qe,mid+1,r,2*i+2));
	}
}
