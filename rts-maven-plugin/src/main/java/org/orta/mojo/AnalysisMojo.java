package org.orta.mojo;

/*-
 * #%L
 * rts-maven-plugin
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



/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import experiments.commons.MavenConstants;
import org.orta.AnalysisSetting;
import org.orta.core.cg.CallGraph;
import org.orta.core.cg.ORTACallGraphBuilder;
import org.orta.core.type.AnalysisSession;
import org.orta.core.type.klass.Klass;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mojo(name = MavenConstants.ANALYSIS, requiresDependencyResolution = ResolutionScope.TEST)
public class AnalysisMojo extends BaseMojo {
  private Map<String, CallGraph> graphs;

  private @NonNull Klass loadKlass(AnalysisSession sess, String name) {
    return sess.getOrCreateKlass(name.replace(".", "/"));
  }

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    File p = getExcludesFile();
    if (p == null) throw new MojoFailureException("excludesFile == null");
    try (AnalysisSession sess = new AnalysisSetting()
            .addClassPath(getTestClassesDirectory().toPath())
            .addClassPath(getClassesDirectory().toPath())
            .excludeMemoryLoader()
            .build()) {
      Set<Klass> klasses = new HashSet<>();
      for (String name : Files.readAllLines(p.toPath()).get(0).split(",")) {
        klasses.add(loadKlass(sess, name));
      }

      ORTACallGraphBuilder.validate(sess, klasses);
    } catch (IOException e) {
      throw mojoException(e);
    }
  }
}
