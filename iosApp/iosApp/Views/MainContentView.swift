//
//  MainContentView.swift
//  iosApp
//
//  Created by Francesco Scalise on 12/01/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainContentView: View {
    @State private var country: Country = Country(name: "Italy")
    @State private var nationalState: NationState = NationState(name: "Calabria")
    @State private var city: City? = nil
    
    var sdk: AirVisualSDK = AirVisualSDK()
    
    var body: some View {
        NavigationView {
            VStack {
                Text("Meteo App 1.0")
                    .font(.largeTitle)
                    .fontWeight(.medium)
                    .foregroundColor(Color.green)
                
                Spacer()
                
                Text("Select country")
                    .font(.headline)
                    .fontWeight(.regular)
                    .foregroundColor(Color.blue)
                NavigationLink(destination: CountrySelectionView(selectedCountry: $country, viewModel: CountrySelectionView.ViewModel.init(sdk: sdk))) {
                    Text(country.name)
                        .frame(minWidth: 0, maxWidth: 300)
                        .padding()
                        .foregroundColor(.white)
                        .background(LinearGradient(gradient: Gradient(colors: [Color.green, Color.blue]), startPoint: .leading, endPoint: .trailing))
                        .cornerRadius(15)
                        .font(.title)
                }
                
                Text("Select nation state")
                    .font(.headline)
                    .fontWeight(.regular)
                    .foregroundColor(Color.blue)
                NavigationLink(destination: NationalStateSelectionView(selectedNationalState: $nationalState, viewModel:  NationalStateSelectionView.ViewModel.init(sdk: sdk, country: country))) {
                    Text(nationalState.name)
                        .frame(minWidth: 0, maxWidth: 300)
                        .padding()
                        .foregroundColor(.white)
                        .background(LinearGradient(gradient: Gradient(colors: [Color.green, Color.blue]), startPoint: .leading, endPoint: .trailing))
                        .cornerRadius(15)
                        .font(.title)
                }
        
                Text("Select city")
                    .font(.headline)
                    .fontWeight(.regular)
                    .foregroundColor(Color.blue)
                    .frame(alignment: .leading)
                    
                NavigationLink(destination: CitySelectionView(selectedCity: $city, viewModel:  CitySelectionView.ViewModel.init(sdk: sdk, country: country, nationState: nationalState ))) {
                    Text(city?.name ?? "-")
                        .frame(minWidth: 0, maxWidth: 300)
                        .padding()
                        .foregroundColor(.white)
                        .background(LinearGradient(gradient: Gradient(colors: [Color.green, Color.blue]), startPoint: .leading, endPoint: .trailing))
                        .cornerRadius(15)
                        .font(.title)
                }
                
                if let city = city {
                    NavigationLink(destination: CityDetailView(viewModel: CityDetailView.ViewModel.init(sdk: sdk, country: country, nationState: nationalState, city: city))) {
                        Text("Show details")
                            .frame(minWidth: 0, maxWidth: 300)
                            .padding()
                            .foregroundColor(.white)
                            .background(LinearGradient(gradient: Gradient(colors: [Color.green, Color.blue]), startPoint: .leading, endPoint: .trailing))
                            .cornerRadius(15)
                            .font(.title)
                    }
                }
                
                Spacer()
            }
        }
    }
}

struct MainContentView_Previews: PreviewProvider {
    static var previews: some View {
        MainContentView()
    }
}
