// 我的题解
class Solution {
public:
    int sub_sum(vector<int>& nums, int l, int r){
        if(l==r)    return nums[l];
        else{
            int mid = (l+r)/2;
            int lsum,rsum;
            lsum = sub_sum(nums, l, mid);
            rsum = sub_sum(nums, mid+1, r);
            int s1,s2,t1,t2,msum;
            s1=nums[mid];t1=0;
            for(int i=mid;i>=l;i--){
                t1+=nums[i];
                if(t1>s1) s1=t1;
            }
            s2=nums[mid+1];t2=0;
            for(int i=mid+1;i<=r;i++){
                t2+=nums[i];
                if(t2>s2) s2=t2;
            }
            msum=s1+s2;
            if(lsum>msum) return (lsum>rsum?lsum:rsum);
            else return (msum>rsum?msum:rsum);
        }
    }
    int maxSubArray(vector<int>& nums) {
        return sub_sum(nums, 0, nums.size()-1);
    }
};

// 官方题解
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int pre = 0, maxAns = nums[0];
        for (const auto &x: nums) {
            pre = max(pre + x, x);
            maxAns = max(maxAns, pre);
        }
        return maxAns;
    }
};
