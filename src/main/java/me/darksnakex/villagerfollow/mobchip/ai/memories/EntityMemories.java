package me.darksnakex.villagerfollow.mobchip.ai.memories;

import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import org.jetbrains.annotations.NotNull;

public final class EntityMemories {
   private static final ChipUtil w = ChipUtil.getWrapper();

   private EntityMemories() {
      throw new UnsupportedOperationException();
   }

   public static boolean registerMemory(@NotNull Memory<?> memory) throws IllegalArgumentException {
      if (memory instanceof EntityMemory) {
         return false;
      } else if (w.existsMemory(memory)) {
         return false;
      } else {
         w.registerMemory(memory);
         return true;
      }
   }

   public static boolean doesMemoryExist(@NotNull Memory<?> memory) {
      return w.existsMemory(memory);
   }
}
