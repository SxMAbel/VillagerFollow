package me.darksnakex.villagerfollow.mobchip.ai.memories;

public enum MemoryStatus {
   PRESENT,
   ABSENT,
   REGISTERED;

   // $FF: synthetic method
   private static MemoryStatus[] $values() {
      return new MemoryStatus[]{PRESENT, ABSENT, REGISTERED};
   }
}
