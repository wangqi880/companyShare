# String 库
### string.byte(s [, i [, j ]])
返回字符 s[i]、s[i + 1]、s[i + 2]、······、s[j] 所对应的 ASCII 码。i 的默认值为 1，即第一个字节,j 的默认值为 i 。

```
print(string.byte("abc", 1, 3))
print(string.byte("abc", 3)) -- 缺少第三个参数，第三个参数默认与第二个相同，此时为 3
print(string.byte("abc"))    -- 缺少第二个和第三个参数，此时这两个参数都默认为 1

-->output
97    98    99
99
97
```
###### 由于 string.byte 只返回整数，而并不像 string.sub 等函数那样（尝试）创建新的 Lua 字符串， 因此使用 string.byte 来进行字符串相关的扫描和分析是最为高效的，尤其是在被 LuaJIT 2 所 JIT 编译之后。

### string.char (...)
接收 0 个或更多的整数（整数范围：0~255），返回这些整数所对应的 ASCII 码字符组成的字符串。当参数为空时，默认是一个 0。

```
print(string.char(96, 97, 98))
print(string.char())        -- 参数为空，默认是一个0，
                            -- 你可以用string.byte(string.char())测试一下
print(string.char(65, 66))

--> output
`ab

AB
```
###### 此函数特别适合从具体的字节构造出二进制字符串。这经常比使用 table.concat 函数和 .. 连接运算符更加高效。

### string.upper(s)
接收一个字符串 s，返回一个把所有小写字母变成大写字母的字符串。

```
print(string.upper("Hello Lua"))  -->output  HELLO LUA
```

### string.lower(s)
接收一个字符串 s，返回一个把所有大写字母变成小写字母的字符串。

```
print(string.lower("Hello Lua"))  -->output   hello lua
```

### string.len(s)
接收一个字符串，返回它的长度。

```
print(string.len("hello lua")) -->output  9
```
###### 使用此函数是不推荐的。应当总是使用 # 运算符来获取 Lua 字符串的长度。

### string.find(s, p [, init [, plain]])
在 s 字符串中第一次匹配 p 字符串。若匹配成功，则返回 p 字符串在 s 字符串中出现的开始位置和结束位置；若匹配失败，则返回 nil。 第三个参数 init 默认为 1，并且可以为负整数，当 init 为负数时，表示从 s 字符串的 string.len(s) + init 索引处开始向后匹配字符串 p 。 第四个参数默认为 false，当其为 true 时，只会把 p 看成一个字符串对待。

```
local find = string.find
print(find("abc cba", "ab"))
print(find("abc cba", "ab", 2))     -- 从索引为2的位置开始匹配字符串：ab
print(find("abc cba", "ba", -1))    -- 从索引为7的位置开始匹配字符串：ba
print(find("abc cba", "ba", -3))    -- 从索引为6的位置开始匹配字符串：ba
print(find("abc cba", "(%a+)", 1))  -- 从索引为1处匹配最长连续且只含字母的字符串
print(find("abc cba", "(%a+)", 1, true)) --从索引为1的位置开始匹配字符串：(%a+)

-->output
1   2
nil
nil
6   7
1   3   abc
nil
```

### string.format(formatstring, ...)
按照格式化参数 formatstring，返回后面 ... 内容的格式化版本。一个指示由字符 % 加上一个字母组成，这些字母指定了如何格式化参数，例如 d 用于十进制数、x 用于十六进制数、o 用于八进制数、f 用于浮点数、s 用于字符串等。

```
print(string.format("%.4f", 3.1415926))     -- 保留4位小数
print(string.format("%d %x %o", 31, 31, 31))-- 十进制数31转换成不同进制
d = 29; m = 7; y = 2015                     -- 一行包含几个语句，用；分开
print(string.format("%s %02d/%02d/%d", "today is:", d, m, y))

-->output
3.1416
31 1f 37
today is: 29/07/2015
```

### string.match(s, p [, init])
在字符串 s 中匹配（模式）字符串 p，若匹配成功，则返回目标字符串中与模式匹配的子串；否则返回 nil。第三个参数 init 默认为 1，并且可以为负整数，当 init 为负数时，表示从 s 字符串的 string.len(s) + init 索引处开始向后匹配字符串 p。

```
print(string.match("hello lua", "lua"))
print(string.match("lua lua", "lua", 2))  --匹配后面那个lua
print(string.match("lua lua", "hello"))
print(string.match("today is 27/7/2015", "%d+/%d+/%d+"))

-->output
lua
lua
nil
27/7/2015
```
###### string.match 目前并不能被 JIT 编译，应 尽量 使用 ngx_lua 模块提供的 ngx.re.match 等接口。

### string.gmatch(s, p)
返回一个迭代器函数，通过这个迭代器函数可以遍历到在字符串 s 中出现模式串 p 的所有地方。

```
s = "hello world from Lua"
for w in string.gmatch(s, "%a+") do  --匹配最长连续且只含字母的字符串
    print(w)
end

-->output
hello
world
from
Lua
```
###### 此函数目前并不能被 LuaJIT 所 JIT 编译，而只能被解释执行。应 尽量 使用 ngx_lua 模块提供的 ngx.re.gmatch 等接口。

### string.rep(s, n)
返回字符串 s 的 n 次拷贝。

```
print(string.rep("abc", 3)) --拷贝3次"abc"

-->output  abcabcabc
```

### string.sub(s, i [, j])
返回字符串 s 中，索引 i 到索引 j 之间的子字符串。当 j 缺省时，默认为 -1，也就是字符串 s 的最后位置。i 可以为负数。当索引 i 在字符串 s 的位置在索引 j 的后面时，将返回一个空字符串。

```
print(string.sub("Hello Lua", 4, 7))
print(string.sub("Hello Lua", 2))
print(string.sub("Hello Lua", 2, 1))    --看到返回什么了吗
print(string.sub("Hello Lua", -3, -1))

-->output
lo L
ello Lua

Lua
```
###### 如果你只是想对字符串中的单个字节进行检查，使用 string.char 函数通常会更为高效。

### string.gsub(s, p, r [, n])
将目标字符串 s 中所有的子串 p 替换成字符串 r。可选参数 n，表示限制替换次数。返回值有两个，第一个是被替换后的字符串，第二个是替换了多少次。

```
print(string.gsub("Lua Lua Lua", "Lua", "hello"))
print(string.gsub("Lua Lua Lua", "Lua", "hello", 2)) --指明第四个参数

-->output
hello hello hello   3
hello hello Lua     2
```
###### 此函数不能为 LuaJIT 所 JIT 编译，而只能被解释执行。一般我们推荐使用 ngx_lua 模块提供的 ngx.re.gsub 函数。

### string.reverse (s)
接收一个字符串 s，返回这个字符串的反转。

```
print(string.reverse("Hello Lua"))  --> output: auL olleH
```
