class Solution(object):
    def simplifyPath(self, path):
        parts = path.split("/")
        
        # 创建一个栈来存储路径部分
        stack = []
        
        # 遍历路径的每个部分
        for part in parts:
            # 如果部分为空或者为当前目录,则跳过
            if part == "" or part == ".":
                continue
            # 如果部分为上级目录,则弹出栈顶元素(如果栈不为空)
            elif part == "..":
                if stack:
                    stack.pop()
            # 否则,将部分压入栈中
            else:
                stack.append(part)
        
        # 将栈中的部分用斜杠连接起来
        simplified_path = "/" + "/".join(stack)
        
        return simplified_path

#示例一
path = "/home/"
solution = Solution()
simplified_path = solution.simplifyPath(path)
print(simplified_path)

#示例2
path = "/../"
solution = Solution()
simplified_path = solution.simplifyPath(path)
print(simplified_path)

示例3
path = "/home//foo/"
solution = Solution()
simplified_path = solution.simplifyPath(path)
print(simplified_path)

示例4
path = "/a/./b/../../c/"
solution = Solution()
simplified_path = solution.simplifyPath(path)
print(simplified_path)