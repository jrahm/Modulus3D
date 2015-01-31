package StandardIO;

import java.io.File;

public abstract interface Approvable
{
  public abstract void onApprove(File paramFile);

  public abstract void onCancel();
}

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.Approvable
 * JD-Core Version:    0.6.2
 */