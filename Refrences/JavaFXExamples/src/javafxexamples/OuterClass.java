package javafxexamples;

public class OuterClass {
    private int outerData = 0;
    private InnerClass iC1;
    private InnerClass iC2;

    public OuterClass()
    {
        iC1 = new InnerClass();
        iC2 = new InnerClass();
    }
    
    public void update() {
        iC1.updateFromInner();
        iC2.updateFromInner();
        iC2.updateFromInner();
    }
    
    public void print() {
        System.out.println(outerData);
        System.out.println(iC1.innerData);
        System.out.println(iC2.innerData);
    }

    public static void main(String[] args)
    {
        OuterClass x = new OuterClass();
        x.update();
        x.print();
    }

    class InnerClass
    {
        private int innerData = 0;
        public void updateFromInner()
        {
            OuterClass.this.outerData++;
            this.innerData--;
        }
    }
}