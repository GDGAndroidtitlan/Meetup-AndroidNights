/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.platzi.androiddesignsupportlibrary.Models;

import com.platzi.androiddesignsupportlibrary.R;

import java.util.Random;

public class Liveclass {

    private static final Random RANDOM = new Random();

    public static int getRandom() {
        switch (RANDOM.nextInt(5)) {
            default:
            case 0:
                return R.mipmap.ic_launcher;
            case 1:
                return R.mipmap.ic_launcher;
            case 2:
                return R.mipmap.ic_launcher;
            case 3:
                return R.mipmap.ic_launcher;
            case 4:
                return R.mipmap.ic_launcher;
        }
    }

    public static final String[] items = {

            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
            "Frontend","Backend","React","Marketing","Programacion Basica","Tutoriales","Canvas",
    };

}
