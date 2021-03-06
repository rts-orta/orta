package experiments.commons;

/*-
 * #%L
 * exp-commons
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



public interface MavenConstants {
  String HASH = "hash";
  String OFFLINE_SELECT_SINGLE = "offline-select-single";
  String OFFLINE_SELECT_SEPARATE = "offline-select-separate";
  String SINGLE_RTA = "singleRTA";
  String ORTA = "ORTA";
  String SEP_RTA = "separateRTA";
  String EDGE_COUNT = "countEdge";
  String ANALYSIS = "analysis";
  String CHECK_TEST = "check-test";
  String GOAL_EDGE_COUNT = "rts:" + EDGE_COUNT;
  String GOAL_CHECK_TEST_RESULT = "rts:" + CHECK_TEST;
  String PARAM_OLD_ARTIFACT = "oldArtifactsRoot";
  String PARAM_ARTIFACT = "artifactsRoot";
  String GOAL_HASH = "rts:" + HASH;
  String GOAL_ANALYSIS = "rts:" + ANALYSIS;
  String GOAL_ORTA = "rts:" + ORTA;
}
