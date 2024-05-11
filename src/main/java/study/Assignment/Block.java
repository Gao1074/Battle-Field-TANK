package study.Assignment;

public class Block extends Entity{
    private boolean destroyable;
    Block(Position position, Size size , boolean destroyable){
        this.position = position;
        this.size = size;
        this.destroyable = destroyable;
    }
    Block(Size size , boolean destroyable){
        this.size = size;
        this.destroyable = destroyable;
    }
    Block(Position position , boolean destroyable){
        this.position = position;
        this.destroyable = destroyable;
    }
    Block(Position position, Size size){
        this.position = position;
        this.size = size;
    }
    Block(Position position){
        this.position = position;
    }
    Block(Size size){
        this.size = size;
    }
    Block(boolean destroyable){
        this.destroyable = destroyable;
    }

    public void setDestroyable(boolean destroyable){
        this.destroyable = destroyable;
    }
    public boolean IsDestroyable(){
        return destroyable;
    }
}
