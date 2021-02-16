package assignment2;

import java.util.Objects;

public class Code
{
   private String code;
   public Code(String code)
   {
      this.code = code;
   }

   @Override
   public String toString()
   {
      return code;
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Code code1 = (Code) o;
      return code.equals(code1.code);
   }

   public String calculateFeedback(Code guess)
   {
      return "String";
   }
}
