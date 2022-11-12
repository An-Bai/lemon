# lemon
纯Java图形化项目——QQ多人在线聊天程序应用


+ [1. 项目介绍](#a)
+ [2. 项目演示](#b)
  + [2.1. 项目启动入口](#b1)
  + [2.2. 开始的登录界面](#b2)
  + [2.3. 注册登录](#b3)
  + [2.4. 个人信息界面](#b4)
  + [2.5. 个人信息修改](#b5)
  + [2.6. 聊天界面](#b6)
  + [2.7. 测试输入](#b7)
  + [2.8. 多人在线登录聊天](#b8)
+ [3. 项目目录说明](#c)
+ [4. 项目使用注意](#d)
+ [随笔](#e)

<br/>

# 1. 项目介绍<span id="a"></span>

+ **项目名**

Lemon

+ **项目详情**

是一个~~仿真~~模拟Web版QQ图形化界面的多人聊天程序，长什么样子下面会有预览啦。

+ **项目功能**

账号密码登录、个人信息展示以及修改、多人在线聊天。

+ **项目主要技术内容（Java基础）**

多线程，IO流文件操作，基于TCP协议网络传输，JFrame容器（Java自带的）

+ **项目开发环境**

eclipse，window10。
哦，刚开始是用的eclipse啦，大家都懂。因为eclipe项目的结构和idea不一样，其实eclipse自己版本之间的项目结构也会有所差异，而且以前的默认编码是GDK（idea是UTF-8），现在2022版本的eclipe默认编码好像是UTF-8了。如果大家要下载的话出现运行问题，建议自己新建一个项目，之后把里面的内容拷贝到自己的项目就好了，反正内容不多。至于项目文件编码的问题，我原本的代码肯定是GDK啦，现在上传的版本编码改成了UTF-8，所以，使用的是最新版的eclipse的话没关系，老版的话设置修改一下Encoding就好了，是idea的话也没问题。


<br/>


# 2. 项目演示<span id="b"></span>

本来是eclipse项目的，但是为了验证移植性，我用idea新建了一个项目然后把内容copy到了这个空项目中，正常启动运行。

### 2.1. 项目启动入口<span id="b1"></span>

src/com/lemon/login 下的 Lemon_Action.java 文件

![在这里插入图片描述](https://img-blog.csdnimg.cn/05e43ad16738427d8dbcb60057fdff52.png#pic_center)


就是一个main程序啦，嘿嘿，然后一开始就开启线程，没有用线程池呀，那时候还不太懂~我也不准备去动它，就保持最初始的模样吧。

<br/>

### 2.2. 开始的登录界面<span id="b2"></span>

项目启动成功后就会弹出登录窗口啦

![在这里插入图片描述](https://img-blog.csdnimg.cn/4b57ffbe530e40918c973d37f10ec9f6.png#pic_center)

<br/>

### 2.3. 注册登录<span id="b3"></span>

那时候嫌麻烦直接把账号密码设死了，因为想着还是开发测试阶段嘛，先弄几个测试账号就好了。点击注册账号就会弹出这个窗口啦，具体账号信息也可以在项目的 Users information 文件夹里面找到。
![在这里插入图片描述](https://img-blog.csdnimg.cn/f59e65ca71284fcebe543033810d7b66.png#pic_center)


<br/>

### 2.4. 个人信息界面<span id="b4"></span>

输入密码点击登录，成功后会弹出个人信息界面啦
![在这里插入图片描述](https://img-blog.csdnimg.cn/057e866b81134bafaaf4b34c0770c41c.png#pic_center)![在这里插入图片描述](https://img-blog.csdnimg.cn/0180c438aceb4366b1c0b053f271f9ba.png#pic_center)


<br/>

### 2.5. 个人信息修改<span id="b5"></span>

点击“编辑资料”，就会弹出下面的窗口啦，我们修改一下，然后看修改后的个人界面信息
![在这里插入图片描述](https://img-blog.csdnimg.cn/2216b1e1723a417d98fba0939d3be696.png#pic_center)

点击确认，修改成功
![在这里插入图片描述](https://img-blog.csdnimg.cn/69db1b51c92b40338409da5eb6cb5e23.png#pic_center)


我再改回去哈

<br/>

### 2.6. 聊天界面<span id="b6"></span>

点击 ENTER 蓝色按钮可以进入聊天界面
![在这里插入图片描述](https://img-blog.csdnimg.cn/28a228bf3a0849ee8b4e47ff02334c12.png#pic_center)



<br/>

### 2.7. 测试输入<span id="b7"></span>

试着输入内容吧
![在这里插入图片描述](https://img-blog.csdnimg.cn/aa3acd3d68ea49fb95f26528b2f2f83c.png#pic_center)


<br/>

### 2.8. 多人在线登录聊天<span id="b8"></span>

那怎么实现多人在线聊天呢，接下来我将演示步骤。其实这里是有关于IDE的区别的，如果用eclipse的话，Run as可以直接再运行一次 Lemon_Action.java 的 main 程序就可以在不关闭前一个运行窗口的前提下再打开一个运行窗口，但是Idea不可以，它直接 Run 的话会默认重新启动项目，这样就不太好了，其实有办法开启多个运行窗口的，接下来我将分别使用Idea和eclipse开启多个运行窗口实现多线程多人在线聊天的功能。

+ **Idea演示**

1）首先我们需要进行多窗口的设置，点击 Edit Configuration... 设置
![在这里插入图片描述](https://img-blog.csdnimg.cn/a5c2e8e336244f3aa13995b04fbc4e21.png#pic_center)


2）照着已有的这个运行窗口配置，我们在新加两个，演示三个人同时在线聊天
![在这里插入图片描述](https://img-blog.csdnimg.cn/58e910f9f1ac4f55b70d309fb0ea2cee.png#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/a3db817f1bd7433ca06b731f6d03598d.png#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/c30586b198d54bbaad290708d309a497.png#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/53157b123808416aa5d3eff8549926f8.png#pic_center)


这样我们就新添加好了两个运行窗口。

2）选择窗口开启运行
![在这里插入图片描述](https://img-blog.csdnimg.cn/bfa737a08b414a228e58a04f49efb534.png#pic_center)


注意：本项目需要每一个账号登录成功后才可以开启新的线程登录下一个用户，绝对不可以同时打开多个登录界面窗口！！！！涉及到服务端的启动原理这里不多解释。如果你不小心一下子一次性打开了多个运行窗口并运行的话，后台会报错，你需要关闭所有线程，并修改配置才可以再次运行，修改配置的话后面再讲。

3）开启第二个运行窗口登录账号
![在这里插入图片描述](https://img-blog.csdnimg.cn/d15475acc36645599ca148fe2db92314.png#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/579a846eb515466cb63c957d1322a689.png#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/e1ec78de09b548a9bb54dbe6d3d836cd.png#pic_center)


ok，现在就有两个账号啦

3）开启第三个运行窗口登录第三个账号
![在这里插入图片描述](https://img-blog.csdnimg.cn/b0edd55675cb47c284a69c35d41048da.png#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/337f8a92567649b1af8366daad7975b7.png#pic_center)


4）打开三个聊天窗口进行聊天
![在这里插入图片描述](https://img-blog.csdnimg.cn/1de1f01e4a7f48ee9e514b33ba4dee83.png#pic_center)




+ **eclipse演示**

eclipse默认运行会开启一个新的窗口，所以不用做配置啦

1）开启第一个运行窗口
![在这里插入图片描述](https://img-blog.csdnimg.cn/8f4b4849db19445a802a8d6b8e42214d.png#pic_center)


2）开启第二个运行窗口

同样的运行 Run as ——> Java application
![在这里插入图片描述](https://img-blog.csdnimg.cn/945533984d1145e4b0b84ba9f76f430e.png#pic_center)


2）开启第三个运行窗口
![在这里插入图片描述](https://img-blog.csdnimg.cn/41eabb30e8874200b5ea16eb3ff37e50.png#pic_center)


后面使用演示是一样的，就不再演示一遍啦。


<br/>


# 3. 项目目录说明<span id="c"></span>
![在这里插入图片描述](https://img-blog.csdnimg.cn/1c458d8b2eb54a4e9b7ad6f84f9a1a4c.png#pic_center)


src：代码目录

Files：

​	IdAndPassword：账号的启动状态，初始化均为false，如果遇到启动问题，将value全部初始化成false即可。

​	ID：账号和密码

Users information：各个账号的信息文件夹



<br/>



# 4. 项目使用注意<span id="d"></span>

+ 开启多人聊天时，一定要一个一个运行mian在登录界面登录成功后在开启下一个，这里涉及到TCP服务的启动判断，有关账号在前情况的功能显示。如果不小心一次开启了多个登录界面，请关闭所有运行窗口，并修改IdAndPassword文件的配置，将所有值重置为false即可。
+ 在使用本项目程序过程中，需要保持正常的关闭程序操作，就是说你想退出登录需要点击右上角的关闭按钮，不可以直接终止程序的运行，不然服务器的启动状态会错误，懂的都懂，你一客户也不可能直接把服务器关了吧。如果你还是这么干了，请重置IdAndPassword配置文件。




















