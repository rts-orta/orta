package org.orta.core.util;

/*-
 * #%L
 * orta-core
 * %%
 * Copyright (C) 2019 https://github.com/rts-orta
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Team ORTA nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */



import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface Suppliers {

  static <@Nullable T> Supplier<T> memorize(@NonNull Supplier<T> supplier) {
    if (supplier instanceof MemorizingSupplier) {
      return supplier;
    }

    return new MemorizingSupplier<>(supplier);
  }

  class MemorizingSupplier<@Nullable T> implements Supplier<T> {

    @Nullable
    private Supplier<T> delegate;
    @Nullable
    private T value;

    MemorizingSupplier(@NonNull Supplier<T> delegate) {
      this.delegate = Preconditions.checkNotNull(delegate);
      this.value = null;
    }

    @Override
    @Nullable
    public T get() {
      if (delegate != null) {
        value = delegate.get();
        delegate = null;
      }

      return value;
    }
  }

  class MemorizingIntSupplier implements IntSupplier {

    @Nullable
    private IntSupplier delegate;
    private int value;

    MemorizingIntSupplier(@NonNull IntSupplier delegate) {
      this.delegate = Preconditions.checkNotNull(delegate);
    }

    @Override
    @Nullable
    public int getAsInt() {
      if (delegate != null) {
        value = delegate.getAsInt();
        delegate = null;
      }

      return value;
    }
  }
}
