# WordSweeper
托大哥的福，接下来是补全修正工作，各位有想法的都加序号写在下面，拿不准的可先在群里讨论。如果自己push了一个改良或修正，readme这里在相应序号后面做标记比如“%”表示已经解决（不要删除原有想法，做做记录挺好的）<br>  我主张这部分鲍寒和小史多commit一点，我们队内好说不过最后教授要看commit分部，这是为了大家都好哈哈哈哈哈。<br>   PS：readme是md文件（markdown），也是种标记语言，各位编辑的时候遇到疑问可以自行查查。<br>
## modify:
### 三个修改的思路：
1.看源码加自己测试找出bug或者自己觉得可以修改的部分。<br>
2.对比之前的类图看有没有需要实现和修改的地方。<br>
3.看之前教授给的需求寻找有没有遗漏的地方。<br>
### 点子：
1.(%)关于后台到gui自动push问题。<br>
2.关于上下左右移动board问题。<br>
3.(%)关于之后登陆之后需要隐藏登录界面的问题。<br>
4.(%)服务器要传Qu, 还是需要string来装等一系列改动。<br>
5.(%)关于protocol没更新问题，需要更新到1.2，具体信息在xsd里。<br>
6.(%)关于LeftBoardPanel类里边是否需要16个button作为属性的问题。<br>
7.跟之前我们类图的比较，有些变动是肯定的，不过是否需要按照正规方式和规定来更改实现方法是个问题。比如game里边是有很多方法的，在gui监听到之后也许应该先调用类里边的方法，再在方法里调controller去send request。目前做到的是先更新entity的信息，等于属性方面正规化了，方法看需要不需要。GUI只是显示层面，级别很低的，感觉GUI里产生的有用信息基本都应该在entity里存在或者经过。
<br>
<br>
大家每次先在eclipse里pull，自己想好了再push，千万不要把其他人的搞错了，避免冲突。今天我差点gg了，不过有回滚所以实在弄错了之后不要太方。
