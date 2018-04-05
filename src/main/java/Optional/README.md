# BorderLayout

BorderLayout是定义在AWT包中的布局管理器。
BorderLayout把容器简单的划分为东、西、南、北、中5个区域，
当使用该布局时，要指明组件添加在哪个区域。
若为指明则默认加入到中间区域。每个区域只能加入一个组件，后加入的组件会覆盖前面一个
```java
BorderLayout(int hgap, int vgap)  //构造一个具有指定组件间距的边框布局

```
# FlowLayout 流式布局
FlowLayout是定义在AWT包中的布局管理器。
FlowLayout默认的对齐方式为居中对齐，可以在实例对象的时候指定对齐方式。
FlowLayout布局方式为自左向右排列，当一行排满后自动换行




# GridLayout 网格布局
GridLayout是定义在AWT包中的布局管理器。
GridLayout布局管理器将组件按照网格方式排列，将容器分成规则矩形块，每个组件尽可能占据每块空间
|方法名|说明|
|---|:---:|
|GridLayout()|创建具有默认值的网格布局，即每个组件占据一行一列|
|GridLayout(int rows, int cols)|创建具有指定行数和列数的网格布局
