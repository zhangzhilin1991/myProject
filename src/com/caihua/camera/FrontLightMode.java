/*
 * Copyright (C) 2012 ZXing authors
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

package com.caihua.camera;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Enumerates settings of the preference controlling the front light.
 */
public enum FrontLightMode {

  /** Always on. */
  ON,
  /** On only when ambient light is low. */
  AUTO,
  /** Always off. */
  OFF;
  
  private static final String KEY_FRONT_LIGHT_MODE="front_light_mode";

  private static FrontLightMode parse(String modeString) {
    return modeString == null ? OFF : valueOf(modeString);
  }

  /**
   * 获取当前闪光灯模式
   * @param sharedPrefs
   * @return
   */
  public static FrontLightMode readPref(SharedPreferences sharedPrefs) {
    return parse(sharedPrefs.getString(KEY_FRONT_LIGHT_MODE, OFF.toString()));
  }
  
  /**
   * 设置闪光灯模式
   * @param sharedPrefs
   * @param mode
   */
  public static final void setPref(SharedPreferences sharedPrefs,FrontLightMode mode){
	  Editor editor=sharedPrefs.edit();
	  editor.putString(KEY_FRONT_LIGHT_MODE,mode.toString());
	  editor.commit();
  }

}
