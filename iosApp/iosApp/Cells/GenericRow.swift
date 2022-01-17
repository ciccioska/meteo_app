//
//  CountryRow.swift
//  iosApp
//
//  Created by Francesco Scalise on 05/01/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GenericRow: View {
    var value: String

    var body: some View {
        HStack() {
            VStack(alignment: .leading, spacing: 10.0) {
                Text(value)
            }
            Spacer()
        }
    }
}
