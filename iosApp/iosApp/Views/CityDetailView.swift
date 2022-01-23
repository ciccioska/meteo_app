//
//  CityDetail.swift
//  iosApp
//
//  Created by Francesco Scalise on 15/01/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CityDetailView: View {
//    @State private var country: Country
//    @State private var nationalState: NationState
//    @State private var city: City
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        loadDetail()
    }
    
    private func loadDetail() -> AnyView {
        switch viewModel.city {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let city):
            return AnyView(Form {
                Text(city.name )
                    .font(.largeTitle)
                    .fontWeight(.medium)
                    .foregroundColor(Color.blue)
                
                Section(header: Text("Weather")) {
                    Text("Temperature: \(city.current?.weather?.temperature ?? 0.0)°C")
                    Text("Pressure: \(city.current?.weather?.pressure ?? 0.0) bar")
                    Text("Humidity: \(city.current?.weather?.humidity ?? 0.0) %")
                    Text("WindSpeed:\(city.current?.weather?.windSpeed ?? 0.0) m/s")
                }
                Section(header: Text("Pollution")) {
                    Text("\(city.current?.pollution?.polluteMainUS ?? "Indictor 1"): \(city.current?.pollution?.pollutionIndicatorUS ?? 0.0)")
                    Text("\(city.current?.pollution?.polluteMainCN ?? "Indictor 2"): \(city.current?.pollution?.pollutionIndicatorCN ?? 0.0)")
                    //Text("\(city?.current?.pollution?.pollutionMainCN): \(city?.current?.pollution?.pollutionIndicatorCN ?? 0.0)")
                }

            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension CityDetailView {
    
    enum CityDetailLoadable {
        case loading
        case result(City)
        case error(String)
    }

    class ViewModel: ObservableObject {
        var currentCountry: Country
        var currentNationState: NationState
        var currentCity: City
        let sdk: AirVisualSDK
        @Published var city = CityDetailLoadable.loading

        init(sdk: AirVisualSDK, country: Country, nationState: NationState, city: City) {
            self.sdk = sdk
            self.currentCountry = country
            self.currentNationState = nationState
            self.currentCity = city
            self.loadCityDetail()
        }

        func loadCityDetail() {
            self.city = .loading
            sdk.getCity(country: self.currentCountry, nationState: self.currentNationState, city: self.currentCity, completionHandler: { city, error in
                if let city = city {
                    self.city = .result(city)
                } else {
                    self.city = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}

